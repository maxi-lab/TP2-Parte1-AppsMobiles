package com.example.tp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random
import kotlin.random.nextInt

class GuessNumber : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {// dentro de estas llaves se pinta en la pantalla del telefono
            //se pueden llamar las funciones que tengan la etiqueta de composable
            //ViewModel - State
            RandomNumberScreen()
        }
    }
    @Composable
    fun RandomNumberScreen(){
        var score by remember {
            mutableIntStateOf(0)
        }
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ScoreGame(score = score)
            for (i in 1..5) {
                Number(i){
                    score += 10
                }
            }
        }
    }
    @Composable
    fun Number(number: Int, onClick:()->Unit) {
        Button(onClick = {
            if (number == Random.nextInt(1..5)) {
                onClick()
            }
            }) {
            Text(text = number.toString())
        }
    }
    @Composable
    fun ScoreGame (score: Int){
        Card() {
            Text(text = score.toString(),
                fontSize = 30.sp,
                modifier = Modifier.padding(8.dp))
        }
    }
}

