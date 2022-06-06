package com.example.flashcards.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.flashcards.databinding.FragmentAddCardBinding
import com.example.flashcards.databinding.FragmentTestBinding

class AddCardFragment : Fragment() {

    private lateinit var binding: FragmentAddCardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddCardBinding.inflate(layoutInflater)

        binding.toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }

        return binding.root
    }
}