package com.example.flashcards.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.flashcards.databinding.FragmentFrontSideBinding
import com.example.flashcards.databinding.FragmentTestBinding

class CardFrontSideFragment : Fragment() {

    private lateinit var binding: FragmentFrontSideBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFrontSideBinding.inflate(layoutInflater)

        binding.toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }

        return binding.root
    }
}