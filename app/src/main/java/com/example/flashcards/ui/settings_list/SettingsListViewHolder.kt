package com.example.flashcards.ui.settings_list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.flashcards.databinding.ItemSettingsBinding
import com.example.flashcards.models.Settings

class SettingsListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(settings: Settings){
        val binding = ItemSettingsBinding.bind(itemView)
        binding.itemSettingsTitle.text = settings.settings_name
    }
}