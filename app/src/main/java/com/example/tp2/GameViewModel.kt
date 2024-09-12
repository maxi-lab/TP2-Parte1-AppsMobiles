package com.example.tp2

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.random.nextInt

class GameViewModel:ViewModel() {
    private lateinit var settingsDataStore:SettingsDataStore
    var gameState by mutableStateOf(GameState())
        private set
    init {
        viewModelScope.launch {}
    }
    fun onClickNumber(number:Int){
        if (number== Random.nextInt(1..5)){
                val prevScore:Int=gameState.score
                gameState=gameState.copy(score =prevScore+10 )
        }else{
               manageAttempts()
        }
        if (gameState.bestScore<gameState.score){
            gameState=gameState.copy(bestScore = gameState.score)
        }
    }
    private fun manageAttempts(){
        val prevAttempts:Int=gameState.attempts
        gameState=gameState.copy(attempts = prevAttempts-1)
        if (gameState.attempts<0){
            gameState=gameState.copy(score = 0,
                attempts = 5)
        }
    }




}