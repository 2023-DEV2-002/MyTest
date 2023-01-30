package com.example.myapplication

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {


    private val currentTime: MutableState<String> = mutableStateOf(getCurrentFormattedTime())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val handler = Handler(mainLooper)

        handler.postDelayed(object : Runnable {
            override fun run() {
                currentTime.value = getCurrentFormattedTime()

                handler.postDelayed(this, 1000)
            }
        }, 0)

        setContent {
            RegularDigitalClock()
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
                text = currentTime.value,
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

}


