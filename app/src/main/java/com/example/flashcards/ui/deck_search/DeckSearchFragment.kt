package com.example.flashcards.ui.card_new

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flashcards.databinding.FragmentDeckSearchBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeckSearchFragment : Fragment() {

    private lateinit var binding: FragmentDeckSearchBinding
    private val viewModel: DeckSearchViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeckSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    companion object {
        val Tag = "deckSearch"

        fun create(): Fragment {
            return DeckSearchFragment()
        }
    }
}