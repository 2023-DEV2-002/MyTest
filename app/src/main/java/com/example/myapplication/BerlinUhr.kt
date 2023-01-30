package com.example.myapplication

import androidx.compose.ui.graphics.Color

class BerlinUhr {







    fun getSecondLampColor(sec: Int): Color {
        return if (sec % 2 == 0)
            Color.Black
         else Color.White
    }


}