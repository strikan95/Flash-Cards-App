package com.example.flashcards.ui.deck_study

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.flashcards.databinding.FragmentDeckStudyBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeckStudyFragment : Fragment() {
    private lateinit var binding: FragmentDeckStudyBinding
    private val viewModel: DeckStudyViewModel by viewModel()
    private val args: DeckStudyFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeckStudyBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.cardState.observe(viewLifecycleOwner, Observer{
            if(viewModel.currentCard == null){
                binding.deckStudyCardText.text = "Study session is over"
                binding.deckStudyTurnCardBtn.visibility = View.GONE
                binding.deckStudyAnswerCardLayout.visibility = View.GONE
            }else{
                if(it == CARD_QUESTION_SHOWN){
                    binding.deckStudyCardText.text = viewModel.currentCard!!.card_title
                    binding.deckStudyTurnCardBtn.visibility = View.VISIBLE
                    binding.deckStudyAnswerCardLayout.visibility = View.GONE
                }else{
                    binding.deckStudyCardText.text = viewModel.currentCard!!.card_body
                    binding.deckStudyTurnCardBtn.visibility = View.GONE
                    binding.deckStudyAnswerCardLayout.visibility = View.VISIBLE
                }
            }
        })

        viewModel.mStartStudy(args.deckId)

        binding.deckStudyTurnCardBtn.setOnClickListener{ showCardAnswer() }

        binding.deckStudyAgainBtn.setOnClickListener{ answerCard(AGAIN_TIME_MS) }
        binding.deckStudyHardBtn.setOnClickListener{ answerCard(HARD_TIME_MS) }
        binding.deckStudyGoodBtn.setOnClickListener{ answerCard(GOOD_TIME_MS) }
        binding.deckStudyEasyBtn.setOnClickListener{ answerCard(4) }
    }

    private fun showCardAnswer(){
        viewModel.showAnswer()
    }

    private fun answerCard(time: Long){
        viewModel.saveAnswerAndLoadNext(time)
    }
}