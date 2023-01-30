package com.example.myapplication

import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {


    private val currentTimeState: MutableState<String> = mutableStateOf(getCurrentFormattedTime())
    private val secondsLampColorState: MutableState<Color> = mutableStateOf(Color.White)


    private val berlinUhr = BerlinUhr()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val handler = Handler(mainLooper)

        handler.postDelayed(object : Runnable {
            override fun run() {
                currentTimeState.value = getCurrentFormattedTime()
                secondsLampColorState.value = berlinUhr.getSecondLampColor(berlinUhr.getSecondsComponent(currentTimeState.value))
                handler.postDelayed(this, 1000)
            }
        }, 0)

        setContent {
            Column(
                modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                SecondsLamp()
                RegularDigitalClock()
            }

        }
    }


    private fun getCurrentFormattedTime(): String {
        return SimpleDateFormat("HH:mm:ss", Locale.GERMANY)
            .format(Date(System.currentTimeMillis()))
            .toString()
    }


    /* Composable functions */

    @Composable
    fun RegularDigitalClock() {
        Box(Modifier.fillMaxWidth()) {
            Text(
                text = currentTimeState.value,
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }


    @Composable
    fun SecondsLamp() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(width = 3.dp, shape = CircleShape, color = Color.Black)
                    .background(secondsLampColorState.value)
            )
        }
    }

}


