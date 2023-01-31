package com.example.myapplication

import androidx.compose.ui.graphics.Color

class BerlinUhr {


    fun splitTimeToIntList(time: String) = time.split(":").map { it.toInt() }

    fun getSecondsComponent(time: String) = splitTimeToIntList(time).last()

    fun getHoursComponent(time: String) = splitTimeToIntList(time).first()

    fun getMinutesComponent(time: String) = splitTimeToIntList(time)[1]


    fun getSecondLampColor(sec: Int): Color {
        return if (sec % 2 == 0)
            Color.Black
        else Color.White
    }


}