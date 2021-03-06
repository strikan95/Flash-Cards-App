package com.example.flashcards.ui.collection_add.deck_download

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flashcards.databinding.FragmentDeckDownloadBinding

class DownloadDeckFragment : Fragment() {

    private lateinit var binding: FragmentDeckDownloadBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeckDownloadBinding.inflate(layoutInflater)
        return binding.root
    }
}