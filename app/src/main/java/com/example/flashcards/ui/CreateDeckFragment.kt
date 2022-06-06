package com.example.flashcards.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flashcards.databinding.FragmentCreateDeckBinding

class CreateDeckFragment : Fragment() {

    private lateinit var binding: FragmentCreateDeckBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateDeckBinding.inflate(layoutInflater)
        return binding.root
    }

    companion object{
        var POSITION_ARG = "postition_arg"
        @JvmStatic
        fun newInstance(position: Int) = CreateDeckFragment().apply {
            arguments = Bundle().apply {
                putInt(POSITION_ARG, position)
            }
        }
    }
}