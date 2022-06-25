package com.example.flashcards.ui.deck_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flashcards.R
import com.example.flashcards.adapters.DeckAdapter
import com.example.flashcards.databinding.FragmentDeckListBinding
import com.example.flashcards.databinding.ItemChipTestBinding
import com.example.flashcards.models.Category
import com.example.flashcards.models.Deck
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.viewModel


class DeckListFragment : Fragment(), OnDeckEventListener {

    private lateinit var binding: FragmentDeckListBinding
    private lateinit var deckAdapter: DeckAdapter
    private val viewModel: DeckListViewModel by viewModel()


    private lateinit var toolbar: MaterialToolbar

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

        viewModel.categories.observe(viewLifecycleOwner){
            if (it != null && it.isNotEmpty()){
                setupChip(it)
                deckAdapter.setCategories(it)
            }
        }

        toolbar = requireActivity().findViewById(R.id.toolbar)
        toolbar.title = "My Collection"

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

    override fun onDeckLongPress(deck: Deck): Boolean {
        viewModel.delete(deck)
        Toast.makeText(context, "Deck deleted!", Toast.LENGTH_SHORT).show()
        return true
    }

    private fun showAddToCollectionAdapter(){

        val action = DeckListFragmentDirections.actionDeckListFragmentToAddToCollectionPagerFragment()
        findNavController().navigate(action)
    }

    private fun setupChip(categories: List<Category>) {
        binding.chipGroup.removeAllViews()
        for (category in categories){
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