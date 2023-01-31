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

    val hoursRowColorListState =
        mutableStateOf(listOf(Color.White, Color.White, Color.White, Color.White))

    val fiveMinutesRowColorListState = mutableStateOf(
        listOf(
            Color.White, Color.White, Color.White, Color.White, Color.White, Color.White,
            Color.White, Color.White, Color.White, Color.White, Color.White
        )
    )

    val minutesRowColorListState = mutableStateOf(
        listOf(Color.White, Color.White, Color.White, Color.White)
    )

    private val handler by lazy { Handler(Looper.getMainLooper()) }

    fun start() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                currentTimeState.value = getCurrentFormattedTime()

                with(currentTimeState.value) {
                    //Updating Blink Lamp
                    updateSecondLampColor(getSecondsComponent(this))

                    //Updating Hours led
                    updateFiveHoursColorsList(getHoursComponent(this))
                    updateHoursColorsList(getHoursComponent(this))

                    //Updating Minutes led
                    updateFiveMinutesColorsList(getMinutesComponent(this))
                    updateMinutesColorsList(getMinutesComponent(this))

                }



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


    fun updateSecondLampColor(sec: Int) {
        secondsLampColorState.value = if (sec % 2 == 0)
            Color.Black
        else Color.White
    }

    fun updateFiveHoursColorsList(hours: Int) {
        val tmpList = mutableListOf(Color.White, Color.White, Color.White, Color.White)

        for (i in 0 until hours / 5) {
            tmpList[i] = Color.Red
        }
        fiveHoursRowColorListState.value = tmpList
    }

    fun updateHoursColorsList(hours: Int) {
        val tmpList = mutableListOf(Color.White, Color.White, Color.White, Color.White)

        for (i in 0 until hours % 5) {
            tmpList[i] = Color.Red
        }
        hoursRowColorListState.value = tmpList
    }

    fun updateFiveMinutesColorsList(minutes: Int) {
        val tmpList = mutableListOf(
            Color.White, Color.White, Color.White, Color.White, Color.White, Color.White,
            Color.White, Color.White, Color.White, Color.White, Color.White
        )

        for (pos in 0 until minutes / 5) {
            tmpList[pos] = if ((pos + 1) % 3 == 0) Color.Red else Color.Yellow
        }

        fiveMinutesRowColorListState.value = tmpList
    }

    fun updateMinutesColorsList(minutes: Int) {
        val tmpList = mutableListOf(Color.White, Color.White, Color.White, Color.White)

        for (pos in 0 until minutes % 5) {
            tmpList[pos] = Color.Yellow
        }

        minutesRowColorListState.value =  tmpList
    }


}