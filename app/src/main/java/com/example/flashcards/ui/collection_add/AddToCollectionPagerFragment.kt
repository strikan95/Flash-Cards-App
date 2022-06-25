package com.example.flashcards.ui.collection_add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.flashcards.adapters.CREATE_DECK_INDEX
import com.example.flashcards.adapters.CollectionAdapter
import com.example.flashcards.adapters.DOWNLOAD_DECK_INDEX
import com.example.flashcards.databinding.FragmentViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator

class AddToCollectionPagerFragment : Fragment() {

    private lateinit var binding: FragmentViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewPagerBinding.inflate(layoutInflater)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = CollectionAdapter(this)

        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            tab.text = getTabTitle(position)
        }.attach()

        return binding.root
    }

    private fun getTabTitle(position: Int): String?{
        return when (position){
            CREATE_DECK_INDEX -> "CREATE A DECK"
            DOWNLOAD_DECK_INDEX -> "SEARCH ONLINE"
            else -> null
        }
    }
}