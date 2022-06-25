package com.example.flashcards.ui.settings_list

import com.example.flashcards.models.Category
import com.example.flashcards.models.Settings

interface OnSettingEventListener {
    fun onSettingsSelected(settings: Settings)
    fun onSettingsLongPress(settings: Settings): Boolean
}