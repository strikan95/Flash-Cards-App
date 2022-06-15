package com.example.flashcards.ui.deck_list

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flashcards.adapters.DeckAdapter
import com.example.flashcards.databinding.FragmentDeckListBinding
import com.example.flashcards.databinding.ItemChipTestBinding
import com.example.flashcards.models.Deck
import com.example.flashcards.models.relationships.DeckWithCards
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel


class DeckListFragment : Fragment(), OnDeckEventListener {

    private lateinit var binding: FragmentDeckListBinding
    private lateinit var deckAdapter: DeckAdapter
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
                deckAdapter.setDecks(it)
                val bla = it
                Log.d(TAG, "onCreateView: fafas")
            }
        }

        viewModel.currentCategory.observe(viewLifecycleOwner){
            if (viewModel.currentCategory.value == null){
                deckAdapter.setDecks(viewModel.decks.value!!)
            }else{
                val filteredDeck = viewModel.decks.value!!.filter { deck ->
                    deck.deck_category_id == it.category_id
                }
                deckAdapter.setDecks(filteredDeck)
            }
        }

        setupChip()

        return binding.root

    }

    private fun setupRecyclerView(){
        binding.deckListRvDecks.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )

        deckAdapter = DeckAdapter()
        deckAdapter.onDeckSelectedListener = this
        binding.deckListRvDecks.adapter = deckAdapter

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

    private fun setupChip() {
        for (category in viewModel.categories){
            val chip = createChip(category.category_name)
            binding.chipGroup.addView(chip)

            chip.setOnClickListener{ click(category.category_id) }
        }
    }

    private fun createChip(label: String): Chip {
        val chip = ItemChipTestBinding.inflate(layoutInflater).root
        chip.text = label
        return chip
    }


    private fun click(id: Long?){

        val decksWithCards: List<DeckWithCards> = viewModel.convertRoomClass(decks = viewModel.decks.value!!)
        val bla: String = "Bla"

        if (viewModel.currentCategory.value == null){
            viewModel.setCurrentCategory(id!!)
        }else{
            if (viewModel.currentCategory.value!!.category_id != id){
                viewModel.setCurrentCategory(id!!)
            }else{
                viewModel.setCurrentCategory(-1)
            }
        }
    }
}