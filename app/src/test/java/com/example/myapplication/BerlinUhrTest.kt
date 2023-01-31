package com.example.myapplication


import androidx.compose.ui.graphics.Color
import org.junit.Assert
import org.junit.Test

class BerlinUhrTest {



    @Test
    fun `convert string time into a list 3 elements`() {
        val berlinUhr = BerlinUhr()
        val time = berlinUhr.splitTimeToIntList("18:05:26")
        Assert.assertEquals(3, time.size)
    }

    @Test
    fun `get seconds component from time string`() {
        val berlinUhr = BerlinUhr()
        val seconds = berlinUhr.getSecondsComponent("18:05:26")
        Assert.assertEquals(26, seconds)
    }

    @Test
    fun `get hours component from time string`() {
        val berlinUhr = BerlinUhr()
        val hours = berlinUhr.getHoursComponent("18:05:26")
        Assert.assertEquals(18, hours)
    }

    @Test
    fun `get minutes component from time string`() {
        val berlinUhr = BerlinUhr()
        val minutes = berlinUhr.getMinutesComponent("18:05:26")
        Assert.assertEquals(5, minutes)
    }


    @Test
    fun `get black color in Second Lamp Color state when odd seconds are shown`() {
        val berlinUhr = BerlinUhr()
        berlinUhr.updateSecondLampColor(44)

        val color = berlinUhr.secondsLampColorState.value
        Assert.assertEquals(Color.Black, color)
    }

    @Test
    fun `get three red colors and one white color in Five Hours State when clocks marks three`() {
        val berlinUhr = BerlinUhr()
        berlinUhr.updateFiveHoursColorsList(15)

        val result = listOf(Color.Red, Color.Red, Color.Red, Color.White)

        val colors = berlinUhr.fiveHoursRowColorListState.value
        Assert.assertEquals(result, colors)
    }
}