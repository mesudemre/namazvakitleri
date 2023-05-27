package com.mesutemre.namazvakitleri

import android.content.res.Resources
import android.util.Log
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mesutemre.namazvakitleri.navigation.NamazvakitleriNavigationItem
import java.io.InputStream

@Composable
fun rememberNamazvakitleriAppState(
    navController: NavHostController = rememberNavController(),
    resources: Resources = resources()
) =
    remember(navController) {
        NamazvakitleriAppState(navController, resources)
    }

@Stable
class NamazvakitleriAppState(
    val navController: NavHostController,
    private val resources: Resources
) {

    fun getStringResource(@StringRes id: Int): String = resources.getString(id)

    fun openRawResource(@RawRes id: Int): InputStream = resources.openRawResource(id)

    private val screenList = NamazvakitleriNavigationItem::class.nestedClasses.map {
        it.objectInstance as NamazvakitleriNavigationItem
    }

    private val currentRoute: String?
        get() = navController.currentDestination?.route

    val shouldShowTopBar: Boolean
        @Composable get() =
            screenList.filter {
                it.screenRoute == navController.currentBackStackEntryAsState().value?.destination?.route
                        &&
                        it.showTopBar
            }.isNullOrEmpty().not()

    fun popBack(
        route: String? = null,
        inclusive: Boolean = false
    ) {
        route?.let {
            navController.popBackStack(
                route = route,
                inclusive = inclusive
            )
        } ?: run {
            navController.popBackStack()
        }
    }

    val screenTitle: String
        get() = getStringResource(screenList.filter {
            it.screenRoute == currentRoute
                    &&
                    it.pageTitle != null
        }[0].pageTitle ?: R.string.common_search)
}

/**
 * A composable function that returns the [Resources]. It will be recomposed when `Configuration`
 * gets updated.
 */
@Composable
@ReadOnlyComposable
private fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}