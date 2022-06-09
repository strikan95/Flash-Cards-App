package com.example.flashcards.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.flashcards.databinding.FragmentDeckListBinding

class DeckListFragment : Fragment() {

    private lateinit var binding: FragmentDeckListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeckListBinding.inflate(layoutInflater)
        binding.addNewDeckBtn.setOnClickListener { showAddToCollectionAdapter() }
        binding.testBtn.setOnClickListener{ showTestFragment() }
        binding.addNewCardBtn.setOnClickListener{ showAddNewCardFragment() }
        binding.viewDeckBtn.setOnClickListener{ showViewDeckFragment() }
        return binding.root

    }

    private fun showAddToCollectionAdapter(){

        val action = DeckListFragmentDirections.actionDeckListFragmentToAddToCollectionPagerFragment()
        findNavController().navigate(action)
    }

    private fun showTestFragment(){
        val action = DeckListFragmentDirections.actionDeckListFragmentToTestFragment2()
        findNavController().navigate(action)
    }

    private fun showAddNewCardFragment(){
        val action = DeckListFragmentDirections.actionDeckListFragmentToAddCardFragment()
        findNavController().navigate(action)
    }

    private fun showViewDeckFragment(){
        val action = DeckListFragmentDirections.actionDeckListFragmentToViewDeckFragment()
        findNavController().navigate(action)
    }
}