package com.example.win39.screen

import android.widget.Toast
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.win39.Number
import com.example.win39.R
import kotlin.math.roundToInt
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@OptIn(
    ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun RouletteScreen() {
    GlideImage(
        model = "http://49.12.202.175/win39/background.jpg",
        contentDescription = "",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
    )
    val number = remember {
        mutableStateOf(0)
    }
    val enabledButton = remember {
        mutableStateOf(false)
    }
    val rotationValue = remember {
        mutableStateOf(0f)
    }
    var text by remember { mutableStateOf("") }
    val context = LocalContext.current
    val angleRot = animateFloatAsState(
        targetValue = rotationValue.value,
        animationSpec = tween(
            durationMillis = 2000
        ),
        finishedListener = {
            val index = (360 - (it % 360)) / (360f / Number.list.size)
            number.value = Number.list[index.roundToInt()]
        }
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = number.value.toString(),
            fontWeight = FontWeight.Bold,
            fontSize = 35.sp,
            color = White,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .wrapContentWidth()
                .height(100.dp)
        )

        val focusManager = LocalFocusManager.current
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.NumberPassword
            ),
            value = text,
            onValueChange = {
                text = it
            },
            placeholder = {
                Text("Pick a number...")
            }
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.roulette),
                contentDescription = context.getString(R.string.roulette),
                modifier = Modifier
                    .fillMaxSize()
                    .rotate(angleRot.value)
            )
            Image(
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = context.getString(R.string.arrow),
                modifier = Modifier.fillMaxSize()
            )
        }
        val context = LocalContext.current
        if (text.isNotEmpty()){
            enabledButton.value = true
        }
        Button(
            enabled = enabledButton.value,
            onClick = {
                rotationValue.value = (720..1080).random().toFloat() + angleRot.value
                Toast.makeText(
                    context,
                    text,
                    Toast.LENGTH_SHORT
                ).show()
                if (text.toInt() == number.value) {
                    Toast.makeText(
                        context,
                        context.resources.getString(R.string.you_won),
                        Toast.LENGTH_SHORT
                    ).show()
                    text = ""
                    enabledButton.value = false
                } else {
                    Toast.makeText(
                        context,
                        context.resources.getString(R.string.you_lose),
                        Toast.LENGTH_SHORT
                    ).show()
                    text = ""
                    enabledButton.value = false
                }
            },
            colors = ButtonDefaults.buttonColors(Red),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = context.getString(R.string.start),
                color = White
            )
        }
    }
}