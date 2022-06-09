package com.example.flashcards.ui.collection_add.deck_new

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.flashcards.databinding.FragmentNewDeckBinding
import com.example.flashcards.ui.viewmodels.NewDeckViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewDeckFragment : Fragment() {

    private lateinit var binding: FragmentNewDeckBinding
    private val viewModel: NewDeckViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewDeckBinding.inflate(layoutInflater)
        binding.createDeckBtn.setOnClickListener{ saveDeck() }
        return binding.root
    }

    private fun saveDeck() {
        val deckName = binding.deckNameInputBox.text.toString()

        viewModel.save(deckName)

        Toast.makeText(context, "Deck Created!", Toast.LENGTH_SHORT).show()
    }

    companion object {
        val Tag = "NewDeck"

        fun create(): Fragment {
            return NewDeckFragment()
        }
    }
}