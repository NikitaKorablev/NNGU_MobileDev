package com.app_lab6.task3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app_lab6.task3.ui.theme.MobileDevTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileDevTheme {
                MovingText()
                MainMenuButton()
            }
        }
    }

    @Composable
    fun MainMenuButton() {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 10.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { finish() },
                modifier =  Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF664FA3) // Фиолетовый цвет
                )
            ) { Text("MainMenu", fontSize = 14.sp) }
        }
    }

    @Composable
    fun MovingText() {
        var isPressed by remember { mutableStateOf(false) }
        val animationSpeed = 1500

        // Анимация вертикальной позиции
        val verticalPosition by animateDpAsState(
            targetValue = if (isPressed) 500.dp else 100.dp,
            animationSpec = tween(durationMillis = animationSpeed), label = ""
        )

        // Анимация вращения
        val rotation by animateFloatAsState(
            targetValue = if (isPressed) 180f else 0f,
            animationSpec = tween(durationMillis = animationSpeed), label = ""
        )

        // Анимация изменения цвета
        val textColor by remember { mutableStateOf(Color.Black) }
        val animatedTextColor by animateColorAsState(
            targetValue = if (isPressed) Color.Blue else textColor,
            animationSpec = tween(durationMillis = animationSpeed), label = ""
        )

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            androidx.compose.foundation.text.BasicText(
                text = "Hello World",
                modifier = Modifier
                    .padding(top = verticalPosition)
                    .rotate(rotation)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onPress = {
                                isPressed = true
                                try {
                                    awaitRelease()
                                } finally {
                                    isPressed = false
                                }
                            }
                        )
                    }
                    .padding(15.dp),
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    color = animatedTextColor
                ),
                softWrap = true
            )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MobileDevTheme {
        Greeting("Android")
    }
}