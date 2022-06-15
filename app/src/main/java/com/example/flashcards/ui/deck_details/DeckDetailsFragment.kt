package com.example.flashcards.ui.deck_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.flashcards.databinding.FragmentDeckDetailsBinding
import com.example.flashcards.models.Deck
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeckDetailsFragment : Fragment() {

    private lateinit var binding: FragmentDeckDetailsBinding
    private val viewModel: DeckDetailsViewModel by viewModel()
    private val args: DeckDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeckDetailsBinding.inflate(layoutInflater)

        binding.deckDetailsNewCardBtn.setOnClickListener{ showNewCardFragment() }
        binding.deckDetailsStudyBtn.setOnClickListener{ showDeckStudyFragment() }

        binding.toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val deck = viewModel.getDeckById(args.deckId)
        display(deck)
    }

    private fun display(deck: Deck?){
        deck?.let {
            binding.apply {
                deckDetailsDeckTitle.text = deck.deck_name
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