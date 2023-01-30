package com.example.myapplication

import androidx.compose.ui.graphics.Color
import org.junit.Assert
import org.junit.Test

class BerlinUhrTest {



    @Test
    fun `get black color when odd seconds are shown`() {
        val berlinUhr = BerlinUhr()
        val color = berlinUhr.getSecondLampColor(44)
        Assert.assertEquals(Color.Black, color)
    }
}