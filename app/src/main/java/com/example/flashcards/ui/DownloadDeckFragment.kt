package com.example.flashcards.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flashcards.databinding.FragmentDownloadDeckBinding

class DownloadDeckFragment : Fragment() {

    private lateinit var binding: FragmentDownloadDeckBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDownloadDeckBinding.inflate(layoutInflater)
        return binding.root
    }
}