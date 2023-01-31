package com.example.myapplication

import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import java.text.SimpleDateFormat
import java.util.*

class BerlinUhr {

    val currentTimeState: MutableState<String> = mutableStateOf(getCurrentFormattedTime())
    val secondsLampColorState: MutableState<Color> = mutableStateOf(Color.White)
    val fiveHoursRowColorListState =
        mutableStateOf(listOf(Color.White, Color.White, Color.White, Color.White))

    init {
        val handler = Handler(Looper.getMainLooper())


        handler.postDelayed(object : Runnable {
            override fun run() {
                currentTimeState.value = getCurrentFormattedTime()
                secondsLampColorState.value =
                    getSecondLampColor(getSecondsComponent(currentTimeState.value))

                updateFiveHoursColorsList(getHoursComponent(currentTimeState.value))

                handler.postDelayed(this, 1000)
            }
        }, 0)
    }


    private fun getCurrentFormattedTime(): String {
        return SimpleDateFormat("HH:mm:ss", Locale.GERMANY)
            .format(Date(System.currentTimeMillis()))
            .toString()
    }


    fun splitTimeToIntList(time: String) = time.split(":").map { it.toInt() }

    fun getSecondsComponent(time: String) = splitTimeToIntList(time).last()

    fun getHoursComponent(time: String) = splitTimeToIntList(time).first()

    fun getMinutesComponent(time: String) = splitTimeToIntList(time)[1]


    fun getSecondLampColor(sec: Int): Color {
        return if (sec % 2 == 0)
            Color.Black
        else Color.White
    }

    private fun updateFiveHoursColorsList(hours: Int) {
        val tmpList = mutableListOf(Color.White, Color.White, Color.White, Color.White)

        for (i in 0 until hours / 5) {
            tmpList[i] = Color.Red
        }
        fiveHoursRowColorListState.value = tmpList
    }


}