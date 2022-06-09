package com.example.flashcards.data.repository

import androidx.lifecycle.LiveData
import com.example.flashcards.models.Card

interface CardRepository {

    fun save(task: Card)
    fun delete(task: Card)
    fun getCardById(id: Long): Card?
    fun getAllCards(): LiveData<List<Card>>
}