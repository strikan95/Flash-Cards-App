package com.example.flashcards.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flashcards.R
import com.example.flashcards.models.Settings
import com.example.flashcards.ui.settings_list.OnSettingEventListener
import com.example.flashcards.ui.settings_list.SettingsListViewHolder

class SettingsAdapter: RecyclerView.Adapter<SettingsListViewHolder>() {

    private val settings = mutableListOf<Settings>()
    var onSettingSelectedListener: OnSettingEventListener? = null

    fun setSettings(settings: List<Settings>){
        this.settings.clear()
        this.settings.addAll(settings)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_settings, parent, false)
        return SettingsListViewHolder(view)
    }

    override fun onBindViewHolder(holder: SettingsListViewHolder, position: Int) {
        val setting = settings[position]
        holder.bind(setting)
        onSettingSelectedListener?.let { listener ->
            holder.itemView.setOnClickListener{ listener.onSettingsSelected(setting) }
            holder.itemView.setOnLongClickListener { listener.onSettingsLongPress(setting) }
        }
    }

    override fun getItemCount(): Int = settings.count()
}