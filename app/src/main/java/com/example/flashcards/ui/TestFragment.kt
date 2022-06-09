package com.example.flashcards.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import com.example.flashcards.databinding.FragmentTestBinding
import com.example.flashcards.models.Card
import com.example.flashcards.ui.viewmodels.CardViewModel

class TestFragment : Fragment() {

    private lateinit var binding: FragmentTestBinding
    private val viewModel: CardViewModel by viewModels()

    private var cardAnswerRequested: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTestBinding.inflate(layoutInflater)
        binding.turnCardBtn.setOnClickListener{showAnswer()}
        binding.againBtn.setOnClickListener{loadNewCard()}
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadNewCard()
    }

    private fun loadNewCard(){
        val card = viewModel.getNextCard()
        if (card != null){
            display(card)
            toggleButtonVisibility()
        }else{
            endStudySession()
        }
    }

    private fun display(card: Card){
        card.let {
            binding.apply {
                binding.cardText.text = card.title
                return
            }
        }
    }

    private fun toggleButtonVisibility(){
        if (cardAnswerRequested) {
            binding.answerTypeBtnLayout.visibility = View.VISIBLE
            binding.turnCardBtn.visibility = View.GONE
        }else
        {
            binding.answerTypeBtnLayout.visibility = View.GONE
            binding.turnCardBtn.visibility = View.VISIBLE
        }
        cardAnswerRequested = !cardAnswerRequested
    }

    private fun showAnswer(){
        toggleButtonVisibility()
        binding.cardText.text = viewModel.currentCard?.body
    }

    private fun endStudySession(){
        binding.answerTypeBtnLayout.visibility = View.GONE
        binding.turnCardBtn.visibility = View.GONE
        binding.cardText.text = "Study session is over"
    }

}