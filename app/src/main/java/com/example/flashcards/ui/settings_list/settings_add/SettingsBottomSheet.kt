package com.example.flashcards.ui.settings_list.settings_add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.example.flashcards.R
import com.example.flashcards.databinding.BottomSheetFragmentSettingsListBinding
import com.example.flashcards.models.Settings
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsBottomSheet(val setting: Settings? = null): BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetFragmentSettingsListBinding
    private val viewModel: SettingsBottomSheetViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetFragmentSettingsListBinding.inflate(layoutInflater)

        binding.settingsNameInputBox.addTextChangedListener {
            viewModel.settingName = it.toString()
            binding.settingsNameInputBox.error = null
        }

        binding.settingsNumNewCardsInputBox.addTextChangedListener {
            viewModel.dailyNewCards = it.toString()
            binding.settingsNumNewCardsInputBox.error = null
        }

        if(setting != null) {
            binding.settingsNameInputBox.setText(setting.settings_name)
            binding.settingsNumNewCardsInputBox.setText(setting.settings_id.toString())
        }

        binding.createSettingBtn.setOnClickListener{
            saveSetting()
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.settingsNameInputBox.setText(viewModel.settingName)
        binding.settingsNumNewCardsInputBox.setText(viewModel.dailyNewCards)
        binding.createSettingBtn.text = if(setting == null) "Save" else "Update"
    }

    private fun saveSetting() {
        if (validateInput()){
            if (setting == null) {
                viewModel.save()
            }else{
                setting.settings_name = viewModel.settingName
                viewModel.update(setting)
            }
            dismiss()
            Toast.makeText(context, if (setting == null){ "Settings saved!" } else { "Settings updated!" }, Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateInput(): Boolean{
        var isValidated: Boolean = true

        if (viewModel.settingName == ""){
            binding.settingsNameInputBox.error = "Field cannot be empty"
            isValidated = false
        }

        if (viewModel.dailyNewCards == ""){
            binding.settingsNumNewCardsInputBox.error = "Must be a number"
            isValidated = false
        }

        return isValidated
    }

    companion object{
        const val TAG = "SettingsBottomSheet"
    }
}