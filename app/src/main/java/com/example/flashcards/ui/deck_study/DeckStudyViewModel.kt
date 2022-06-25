package com.example.flashcards.ui.deck_study

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flashcards.data.repository.card.CardRepository
import com.example.flashcards.data.repository.deck.DeckRepository
import com.example.flashcards.models.Card
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.concurrent.TimeUnit

const val CARD_QUESTION_SHOWN = 0
const val CARD_ANSWER_SHOWN = 1

const val SECOND_MS: Long = 1000

const val AGAIN_TIME_MS: Long = SECOND_MS*5
const val HARD_TIME_MS: Long = SECOND_MS*10
const val GOOD_TIME_MS: Long = SECOND_MS*60*60*24

const val NUM_OF_NEW_CARDS = 3

class DeckStudyViewModel(
    val cardRepository : CardRepository,
    val deckRepository: DeckRepository
) : ViewModel() {
    private var _cardState = MutableLiveData<Int>()
    var cardState: LiveData<Int> = _cardState

    var currentCard: Card? = null

    private var cardsToRevise = mutableListOf<Card>()
    private var cardQueue = LinkedList<Card>()


    fun mStartStudy(id: Long){
        // Current time
        val currentTime = System.currentTimeMillis()

        // Fetch cards
        val allCards = cardRepository.getAllCardsFromDeck(id)
        allCards.sortedWith(nullsLast(compareBy { it.card_deck_id }))

        // Filer all cards that need to be revised today
        cardsToRevise = allCards.filter { card: Card ->
            card.card_last_date_revised != null &&
                    card.card_last_date_revised!!.time - currentTime < 0} as MutableList<Card>

        // Fllter cards that were never studied
        val newCards = allCards.filter { card: Card ->
            card.card_last_date_revised == null
        }

        // Append cards that need to be revised and N (or whats left) new cards
        if (newCards.isNotEmpty()){
            if (newCards.size < NUM_OF_NEW_CARDS){
                cardsToRevise.addAll(newCards)
            }else{
                cardsToRevise.addAll(newCards.take(NUM_OF_NEW_CARDS))
            }
        }

        // Is there any cards to study?
        if (cardsToRevise.isEmpty()){
            TODO()
        }else{
            // Get first card
            currentCard = cardsToRevise.firstOrNull()
            cardsToRevise.removeAt(0)

            // Show card question by setting the cardstate thats connected to the observer
            _cardState.value = CARD_QUESTION_SHOWN
        }
    }

        //var index = list.indexOf(list.first{ it > 20 })

        //var bla = list.first{ it < 20 }

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveAnswerAndLoadNext(confidence: Long){
        if (currentCard != null){

            var newTime: Long = 0

            // Get current time
            val currentTime = System.currentTimeMillis()
            val timeInDays = TimeUnit.MILLISECONDS.toDays(currentTime)
            val dayInMillis = TimeUnit.DAYS.toMillis(1)
            val minuteInMillis = TimeUnit.MINUTES.toMillis(1)

            val truncatedTime = TimeUnit.MILLISECONDS.toDays(currentTime)

            var addedDay = TimeUnit.DAYS.toMillis(truncatedTime + 1)

            val day = ChronoUnit.MILLIS.duration.toDays()

            // Take the copy of current card
            val tmpCard: Card = currentCard!!


            // Update last revised time
            if (confidence < GOOD_TIME_MS){
                newTime = currentTime + confidence
            }else{
                newTime = TimeUnit.DAYS.toMillis(timeInDays + 1)
            }

            // Edit time that the card will be revised
            tmpCard.card_last_date_revised = Date(newTime)

            // Update db
            cardRepository.updateLastRevised(tmpCard.card_last_date_revised, tmpCard.card_deck_id, tmpCard.card_id)


            // If the confidence is less than good add it back to queue
            if(confidence < GOOD_TIME_MS){
                val i = cardQueue.indexOf(cardQueue.firstOrNull(){
                    it.card_last_date_revised != null &&
                    it.card_last_date_revised!!.time > newTime })

                if (i == -1){
                    cardQueue.offer(tmpCard)
                } else{
                    cardQueue.add(i, tmpCard)
                }
            }

            // Load next card
            if (cardQueue.isEmpty()){
                if (cardsToRevise.isEmpty()){
                    // No more cards to study
                    currentCard = null
                }else{
                    // If queue is empty get first from revision list
                    currentCard = cardsToRevise.first()
                    cardsToRevise.removeAt(0)
                }
            }else{
                if (cardQueue.peek()!!.card_last_date_revised!!.time - currentTime < 0){
                    // Show first card from queue if its time is up
                    currentCard = cardQueue.pollFirst()
                }else{
                    if (cardsToRevise.isNotEmpty()){
                        // Get the card from revision list if time is not up
                        currentCard = cardsToRevise.first()
                        cardsToRevise.removeAt(0)
                    }else{
                        // If no more cards from revision list and time is not up show the first card from queue anyway
                        currentCard = cardQueue.pollFirst()
                    }
                }
            }

            showQuestion()
        }
    }

    fun showAnswer(){
        _cardState.value = CARD_ANSWER_SHOWN
    }

    private fun showQuestion(){
        _cardState.value = CARD_QUESTION_SHOWN
    }
}