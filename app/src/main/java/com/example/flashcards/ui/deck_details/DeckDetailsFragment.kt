package com.example.flashcards.ui.deck_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.flashcards.R
import com.example.flashcards.databinding.FragmentDeckDetailsBinding
import com.example.flashcards.models.Deck
import com.example.flashcards.models.relationships.DeckWithCards
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import java.util.concurrent.TimeUnit

class DeckDetailsFragment() : Fragment() {

    private lateinit var binding: FragmentDeckDetailsBinding
    private val args: DeckDetailsFragmentArgs by navArgs()
    private val viewModel: DeckDetailsViewModel by viewModel()

    private lateinit var toolbar: MaterialToolbar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeckDetailsBinding.inflate(layoutInflater)
        binding.deckDetailsNewCardBtn.setOnClickListener{ showNewCardFragment() }
        binding.deckDetailsStudyBtn.setOnClickListener{ showDeckStudyFragment() }


        toolbar = requireActivity().findViewById(R.id.toolbar)
        toolbar.title = null
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setDeckWithCards(args.deckId)
        display(viewModel.getDeckWithCards().value)
    }

    private fun display(deck: DeckWithCards?){
        deck?.let {
            binding.apply {
                toolbar.title = deck.deck.deck_name

                val numOfNewCards = deck.cards.count {
                    it.card_last_date_revised == null
                }
                if (numOfNewCards < 3){
                    numNewCards.text = numOfNewCards.toString()
                }else{
                    numNewCards.text = 3.toString()
                }

                TimeUnit.DAYS.toMillis(viewModel.timeInDays)

                numCardsToRevise.text = deck.cards.count {
                    it.card_last_date_revised != null &&
                            it.card_last_date_revised!!.time <=
                            TimeUnit.DAYS.toMillis(viewModel.timeInDays)
                }.toString()

                numCardsDue.text = deck.cards.count {
                    it.card_last_date_revised != null &&

                    it.card_last_date_revised!!.time >=
                    (TimeUnit.DAYS.toMillis(viewModel.timeInDays) + 1) &&

                    it.card_last_date_revised!!.time <=
                    (TimeUnit.DAYS.toMillis(viewModel.timeInDays + 1) - 1)
                }.toString()
            }
        }
    }

    private fun showNewCardFragment(){
        val action = DeckDetailsFragmentDirections.actionDeckDetailsFragmentToNewCardFragment(args.deckId)
        findNavController().navigate(action)
    }

    private fun showDeckStudyFragment(){
        val action = DeckDetailsFragmentDirections.actionDeckDetailsFragmentToDeckStudyFragment(args.deckId)
        findNavController().navigate(action)
    }

    companion object{
        val Tag = "DeckDetails"
        val DeckIdKey = "DeckId"

        fun create(id: Long): Fragment {
            val fragment = DeckDetailsFragment()
            return fragment
        }
    }
}