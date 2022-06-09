package com.example.flashcards.ui.deck_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flashcards.adapters.CollectionAdapter
import com.example.flashcards.adapters.DeckAdapter
import com.example.flashcards.databinding.FragmentDeckListBinding
import com.example.flashcards.ui.viewmodels.DeckListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeckListFragment : Fragment(), OnDeckEventListener {

    private lateinit var binding: FragmentDeckListBinding
    private lateinit var adapter: DeckAdapter
    private val viewModel: DeckListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeckListBinding.inflate(layoutInflater)

        // Buttons
        binding.addNewDeckBtn.setOnClickListener { showAddToCollectionAdapter() }

        // Setup the recycler view
        setupRecyclerView()
        viewModel.decks.observe(viewLifecycleOwner) {
            if (it != null && it.isNotEmpty()){
                adapter.setDecks(it)
            }
        }
        return binding.root

    }


    private fun setupRecyclerView(){
        binding.deckListRvDecks.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )

        adapter = DeckAdapter()
        adapter.onDeckSelectedListener = this
        binding.deckListRvDecks.adapter = adapter
    }

    companion object {
        val Tag = "DeckList"
        fun create(): Fragment {
            return DeckListFragment()
        }
    }

    override fun onDeckSelected(id: Long?) {
        val action =
            DeckListFragmentDirections.actionDeckListFragmentToDeckDetailsFragment(id ?: -1)
        findNavController().navigate(action)
    }

    private fun showAddToCollectionAdapter(){

        val action = DeckListFragmentDirections.actionDeckListFragmentToAddToCollectionPagerFragment()
        findNavController().navigate(action)
    }
}