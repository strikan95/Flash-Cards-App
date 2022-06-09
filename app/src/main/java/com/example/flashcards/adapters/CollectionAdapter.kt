package com.example.flashcards.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.flashcards.ui.collection_add.deck_new.NewDeckFragment
import com.example.flashcards.ui.collection_add.deck_download.DownloadDeckFragment
import java.lang.IndexOutOfBoundsException

const val CREATE_DECK_INDEX = 0
const val DOWNLOAD_DECK_INDEX = 1

class CollectionAdapter(fragment: Fragment): FragmentStateAdapter(fragment){

    private val tabFragmentsCreators: Map<Int, ()->Fragment> = mapOf(
        CREATE_DECK_INDEX to { NewDeckFragment() },
        DOWNLOAD_DECK_INDEX to { DownloadDeckFragment() }
    )


    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int) :Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}