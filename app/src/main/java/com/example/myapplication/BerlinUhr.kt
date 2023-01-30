package com.example.myapplication

import androidx.compose.ui.graphics.Color

class BerlinUhr {


    fun splitTimeToIntList(time: String) = time.split(":").map { it.toInt() }


    fun getSecondLampColor(sec: Int): Color {
        return if (sec % 2 == 0)
            Color.Black
        else Color.White
    }


}