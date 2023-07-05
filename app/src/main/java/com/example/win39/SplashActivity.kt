package com.example.win39

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.win39.ui.theme.RedButton
import com.example.win39.ui.theme.Win39Theme

class SplashActivity : ComponentActivity() {

    @OptIn(ExperimentalGlideComposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GlideImage(
                model = "http://49.12.202.175/win39/background.jpg",
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = resources.getString(R.string.app_name),
                    fontSize = 54.sp,
                    color = RedButton,
                    fontWeight = FontWeight.Bold
                )
                GlideImage(
                    modifier = Modifier
                        .size(width = 120.dp, height = 120.dp),
                    model = "http://49.12.202.175/win39/logo.png",
                    contentDescription = "",
                )
                CircularProgressIndicator(modifier = Modifier.padding(top = 128.dp))
            }
            Handler().postDelayed({
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }, 3000)
        }
    }
}