package com.example.flashcards.ui.card_new

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.flashcards.databinding.FragmentNewCardBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewCardFragment : Fragment() {

    private lateinit var binding: FragmentNewCardBinding
    private val viewModel: NewCardViewModel by viewModel()
    private val args: NewCardFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewCardBinding.inflate(layoutInflater)
        binding.addNewCardBtn.setOnClickListener{ saveCard() }

        binding.toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }
        return binding.root
    }

    private fun saveCard() {
        val title = binding.cardTitleInputBox.text.toString()
        val body = binding.cardBodyInputBox.text.toString()

        viewModel.save(args.deckId, title, body)

        Toast.makeText(context, "Card added", Toast.LENGTH_SHORT).show()

        binding.cardTitleInputBox.text.clear()
        binding.cardBodyInputBox.text.clear()
    }

    companion object {
        val Tag = "NewTask"

        fun create(): Fragment {
            return NewCardFragment()
        }
    }
}