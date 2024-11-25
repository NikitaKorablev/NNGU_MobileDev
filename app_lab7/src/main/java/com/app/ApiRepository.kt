package com.app

import android.util.Log

class ApiRepository {

    private val apiService = ApiService.create()

    suspend fun checkWordExistence(word: String): List<WordResponse> {
        try {
            val response = apiService.checkWord(word)
            return response
        } catch (e: Exception) {
            Log.e("Main", "Ошибка: ${e.message}")
            return emptyList()
        }
    }
}

