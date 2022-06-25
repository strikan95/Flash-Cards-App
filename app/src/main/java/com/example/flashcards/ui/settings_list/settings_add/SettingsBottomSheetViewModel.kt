package com.example.flashcards.ui.settings_list.settings_add

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.flashcards.data.repository.settings.SettingsRepository
import com.example.flashcards.models.Settings

class SettingsBottomSheetViewModel(
    val settingsRepository: SettingsRepository
) : ViewModel() {

    var settingName: String = ""
    var dailyNewCards: String = ""

    fun save(){
        settingsRepository.save(Settings(0, settingName))
    }

    fun update(settings: Settings){
        settingsRepository.update(settings)
    }
}