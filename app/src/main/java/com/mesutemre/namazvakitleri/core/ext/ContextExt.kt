package com.mesutemre.namazvakitleri.core.ext

import android.app.Activity
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentResolver
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.widget.RemoteViews
import androidx.annotation.StringRes
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.*
import com.mesutemre.namazvakitleri.R
import com.mesutemre.namazvakitleri.core.Constants
import com.squareup.picasso.Picasso
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.concurrent.TimeUnit

fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("no activity")
}

fun Context.createAppChannel(
    channelId: String,
    channelName: String,
    importance: Int = NotificationManager.IMPORTANCE_HIGH,
    @StringRes description: Int
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            channelId,
            channelName,
            importance
        )
        channel.description = getString(description)
        channel.setSound(
            Uri.parse("${ContentResolver.SCHEME_ANDROID_RESOURCE}://${packageName}/${R.raw.cuma_sound}"),
            Notification.AUDIO_ATTRIBUTES_DEFAULT
        )
        channel.enableVibration(true)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

fun Context.createCumaHatirlaticiNotification() {
    val largeIcon = BitmapFactory.decodeResource(this.resources, R.drawable.cuma_large)
    val notification = NotificationCompat.Builder(
        this,
        Constants.ChannelConstants.CUMA_HATIRLATICI_CHANNEL_ID
    ).setSmallIcon(R.mipmap.ic_launcher)
        .setLargeIcon(largeIcon)
        .setContentTitle(this.getString(R.string.notification_cuma_hatirlatici_title))
        .setContentText(this.getString(R.string.notification_cuma_hatirlatici_content))
        .setSound(
            Uri.parse(
                ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                        packageName + "/" + R.raw.cuma_sound
            )
        )
        .setStyle(
            NotificationCompat.BigPictureStyle().bigPicture(largeIcon).bigLargeIcon(null)
        )
        .setVibrate(longArrayOf(0, 100, 200, 300))
        .build()
    NotificationManagerCompat.from(this).notify(73195, notification)
}

inline fun <reified W : ListenableWorker>
        Context.enqueeOneTimeWorkManager(
    constraints: Constraints = Constraints.Builder()
        .build()
) {
    val otwr = OneTimeWorkRequestBuilder<W>()
        .setConstraints(constraints)
        .build()
    WorkManager.getInstance(this).enqueue(otwr);
}

inline fun <reified W : ListenableWorker>
        Context.enqueePeriodicTimeWorkManager(
    workTag: String,
    repeatInterval: Int = 1,
    period: TimeUnit = TimeUnit.DAYS,
    existingPeriodicWorkPolicy: ExistingPeriodicWorkPolicy = ExistingPeriodicWorkPolicy.KEEP,
    constraints: Constraints = Constraints.Builder()
        .build()
) {
    val pwr = PeriodicWorkRequestBuilder<W>(repeatInterval.toLong(), period)
        .setConstraints(constraints)
        .addTag(workTag)
        .build();
    WorkManager.getInstance(this)
        .enqueueUniquePeriodicWork(workTag, existingPeriodicWorkPolicy, pwr)

}

fun Context.createVakitHatirlaticiNotification(
    title: String,
    description: String
) {
    val notificationLayout = RemoteViews(this.packageName, R.layout.notification_vakit_hatirlatici)
    val notificationLayoutExpanded =
        RemoteViews(this.packageName, R.layout.notification_vakit_hatirlatici_expand)

    notificationLayout.setTextViewText(R.id.notificationCollapseTitleId, title)
    notificationLayout.setTextViewText(R.id.notificationCollapseDescriptionId, description)

    notificationLayoutExpanded.setTextViewText(R.id.notificataionExpandTitleId, title)
    notificationLayoutExpanded.setTextViewText(R.id.notificataionExpandDescriptionId, description)

    val notification = NotificationCompat.Builder(
        this,
        Constants.ChannelConstants.VAKIT_PUSH_CHANNEL_ID
    ).setSmallIcon(R.mipmap.ic_launcher)
        .setContentTitle(title)
        .setContentText(description)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setSound(
            Uri.parse(
                ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                        packageName + "/" + R.raw.cuma_sound
            )
        )
        .setVibrate(longArrayOf(0, 100, 200, 300))
        .setStyle(NotificationCompat.DecoratedCustomViewStyle())
        .setCustomContentView(notificationLayout)
        .setCustomBigContentView(notificationLayoutExpanded)
        .build()

    NotificationManagerCompat.from(this).notify(73196, notification)
}

fun Context.isNetworkAvaliable(): Boolean {
    val connectivityManager =
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (connectivityManager != null) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                return true
            }
        }
    }
    return false
}

fun Context.shareTextAndImageContent(
    imageUrl: String,
    message: String,
    title: String,
    onPrepareLoad:(Boolean) -> Unit
) {
    Picasso.get().load(imageUrl).into(object : com.squareup.picasso.Target {
        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_STREAM, getBitmapFromView(bitmap))
            intent.putExtra(Intent.EXTRA_TEXT, message)
            onPrepareLoad(false)
            startActivity(Intent.createChooser(intent, title))
        }

        override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
            onPrepareLoad(false)
        }

        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            onPrepareLoad(true)
        }

    })
}

fun Context.getBitmapFromView(bmp: Bitmap?): Uri? {
    var bmpUri: Uri? = null
    try {
        val file = File(this.externalCacheDir, System.currentTimeMillis().toString() + ".jpg")

        val out = FileOutputStream(file)
        bmp?.compress(Bitmap.CompressFormat.JPEG, 90, out)
        out.close()
        bmpUri = Uri.fromFile(file)

    } catch (e: IOException) {
        e.printStackTrace()
    }
    return bmpUri
}