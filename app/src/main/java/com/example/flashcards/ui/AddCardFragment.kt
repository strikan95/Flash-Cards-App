package com.example.flashcards.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.flashcards.databinding.FragmentAddCardBinding
import com.example.flashcards.databinding.FragmentTestBinding
import com.example.flashcards.ui.viewmodels.NewCardViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddCardFragment : Fragment() {

    private lateinit var binding: FragmentAddCardBinding
    private val viewModel: NewCardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddCardBinding.inflate(layoutInflater)
        binding.addNewCardBtn.setOnClickListener{ saveCard() }

        binding.toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }
        return binding.root
    }

    private fun saveCard() {
        val title = binding.cardTitleInputBox.text.toString()
        val body = binding.cardBodyInputBox.text.toString()

        viewModel.save(title, body)

        Toast.makeText(context, "Card added", Toast.LENGTH_SHORT).show()
    }

    companion object {
        val Tag = "NewTask"

        fun create(): Fragment {
            return AddCardFragment()
        }
    }
}