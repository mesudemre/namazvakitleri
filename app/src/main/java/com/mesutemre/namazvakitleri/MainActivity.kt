package com.mesutemre.namazvakitleri

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NamazvakitleriTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = NamazvakitleriTheme.colors.uiBackground
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", color = NamazvakitleriTheme.colors.currentVakit)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NamazvakitleriTheme {
        Greeting("Android")
    }
}