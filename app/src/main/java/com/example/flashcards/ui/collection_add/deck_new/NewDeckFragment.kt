package com.example.flashcards.ui.collection_add.deck_new

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.get
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.flashcards.databinding.FragmentNewDeckBinding
import com.example.flashcards.databinding.ItemChipTestBinding
import com.example.flashcards.models.Category
import com.example.flashcards.models.Settings
import com.example.flashcards.ui.collection_add.AddToCollectionPagerFragmentDirections
import com.example.flashcards.ui.deck_list.DeckListFragmentDirections
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.viewModel


class NewDeckFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: FragmentNewDeckBinding
    private val viewModel: NewDeckViewModel by viewModel()

    private lateinit var spinnerAdapter: ArrayAdapter<Settings>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewDeckBinding.inflate(layoutInflater)

        binding.deckNameInputBox.addTextChangedListener {
            viewModel.deckName = it.toString()
            binding.deckNameInputBox.error = null
        }


        binding.createDeckBtn.setOnClickListener{ saveDeck() }

        spinnerAdapter =
            ArrayAdapter<Settings>(requireContext(), R.layout.simple_spinner_item, ArrayList())
        spinnerAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.settingsSpinner.adapter = spinnerAdapter

        viewModel.settings.observe(viewLifecycleOwner){
            if (it != null && it.isNotEmpty()){
                setupSpinner(it)
            }
        }


        viewModel.categories.observe(viewLifecycleOwner){
            if (it != null && it.isNotEmpty()){
                setupChip(it)
            }
        }


        return binding.root
    }


    private fun saveDeck() {
        if(validateInput()){
            viewModel.save()
            Toast.makeText(context, "Deck Created!", Toast.LENGTH_SHORT).show()
            val action = AddToCollectionPagerFragmentDirections.actionAddToCollectionPagerFragmentToDeckListFragment()
            findNavController().navigate(action)
        }
    }

    private fun validateInput(): Boolean{
        var isValidated: Boolean = true

        if (viewModel.deckName == ""){
            binding.deckNameInputBox.error = "Field cannot be empty"
            isValidated = false
        }

        return isValidated
    }

    private fun setupSpinner(settings: List<Settings>){
        for (setting in settings){
            spinnerAdapter.add(setting)
        }
        binding.settingsSpinner.onItemSelectedListener = this

    }

    private fun setupChip(categories: List<Category>) {
        for (category in categories){
            val chip = createChip(category.category_name)
            binding.nedDeckChipGroup.addView(chip)

            chip.setOnClickListener{ click(category) }
        }
    }

    private fun createChip(label: String): Chip {
        val chip = ItemChipTestBinding.inflate(layoutInflater).root
        chip.text = label
        return chip
    }

    private fun click(category: Category){
        viewModel.selectedCategory = category
    }

    companion object {
        val Tag = "NewDeck"

        fun create(): Fragment {
            return NewDeckFragment()
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        val selected = parent.adapter.getItem(pos) as Settings
        viewModel.selectedSetting = selected
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}
}