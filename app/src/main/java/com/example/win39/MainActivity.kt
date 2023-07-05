package com.example.win39

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
import com.example.win39.screen.RouletteScreen
import com.example.win39.ui.theme.GreenBackground
import com.example.win39.ui.theme.Win39Theme
import com.onesignal.OneSignal

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
            OneSignal.initWithContext(this)
            OneSignal.setAppId("714b9f14-381d-4fc4-a93c-28d480557381")
            Win39Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = GreenBackground
                ) {
                    RouletteScreen()
                }
            }
        }
    }
}

