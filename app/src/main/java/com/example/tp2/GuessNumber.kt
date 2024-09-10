package com.example.tp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
            val gameViewModel by viewModels<GameViewModel> ()
            RandomNumberScreen(gameViewModel)
        }
    }
    @Composable
    fun RandomNumberScreen(gameViewModel:GameViewModel){

        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            BestScore(bestScore = gameViewModel.gameState.bestScore)
            AttemptsGame(attempts = gameViewModel.gameState.attempts)
            ScoreGame(score =gameViewModel.gameState.score)
            for (i in 1..5) {
                Number(i){
                    gameViewModel.onClickNumber(i)
                }
            }
        }
    }
    @Composable
    fun Number(number: Int, onClick:()->Unit) {
        Button(onClick = { onClick()}) {
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
    @Composable
    fun AttemptsGame(attempts:Int){
        Card {
            Text(text = attempts.toString(),
                fontSize = 30.sp,
                modifier = Modifier.padding((8.dp))
                )
        }
    }
    @Composable
    fun BestScore(bestScore:Int){
        Card {
            Text(text = bestScore.toString(),
                fontSize = 30.sp,
                modifier = Modifier.padding((8.dp))
            )
        }
    }
}

