package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    private val berlinUhr = BerlinUhr()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        berlinUhr.start()
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                SecondsLamp()
                FiveHoursRow()
                HourRow()
                FiveMinutesRow()
                RegularDigitalClock()
            }

        }
    }


    /* Composable functions */

    @Composable
    fun RegularDigitalClock() {
        Box(Modifier.fillMaxWidth()) {
            Text(
                text = berlinUhr.currentTimeState.value,
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    @Composable
    fun FiveHoursRow() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            for (item in berlinUhr.fiveHoursRowColorListState.value) {
                Box(
                    modifier = Modifier
                        .size(80.dp, 50.dp)
                        .clip(RectangleShape)
                        .padding(7.dp)
                        .border(width = 2.dp, color = Color.Black)
                        .background(item)
                )
            }
        }
    }

    @Composable
    fun HourRow() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            for (item in berlinUhr.hoursRowColorListState.value) {
                Box(
                    modifier = Modifier
                        .size(80.dp, 50.dp)
                        .clip(RectangleShape)
                        .padding(7.dp)
                        .border(width = 2.dp, color = Color.Black)
                        .background(item)
                )
            }
        }
    }


    @Composable
    fun FiveMinutesRow() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            for (item in berlinUhr.fiveMinutesRowColorListState.value) {
                Box(
                    modifier = Modifier
                        .size(28.dp, 60.dp)
                        .clip(RectangleShape)
                        .padding(5.dp)
                        .border(width = 2.dp, color = Color.Black)
                        .background(item)
                )
            }
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
                    .background(berlinUhr.secondsLampColorState.value)
            )
        }
    }

}


