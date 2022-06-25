package com.example.flashcards.ui.settings_list

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flashcards.R
import com.example.flashcards.adapters.SettingsAdapter
import com.example.flashcards.databinding.FragmentSettingsListBinding
import com.example.flashcards.models.Deck
import com.example.flashcards.models.Settings
import com.example.flashcards.ui.settings_list.settings_add.SettingsBottomSheet
import com.google.android.material.appbar.MaterialToolbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsListFragment : Fragment(), OnSettingEventListener {

    private lateinit var binding: FragmentSettingsListBinding
    private lateinit var adapter: SettingsAdapter
    private val viewModel: SettingViewModel by viewModel()

    private lateinit var toolbar: MaterialToolbar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsListBinding.inflate(layoutInflater)

        binding.addNewSettingBtn.setOnClickListener{
            showBottomSheet()
        }

        setupRecyclerView()

        viewModel.settings.observe(viewLifecycleOwner) {
            if ((it != null) && it.isNotEmpty()){
                adapter.setSettings(it)
            }
        }

        toolbar = requireActivity().findViewById(R.id.toolbar)
        toolbar.title = "My Settings"

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.settingsListRvSettings.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )

        adapter = SettingsAdapter()
        adapter.onSettingSelectedListener = this
        binding.settingsListRvSettings.adapter = adapter
    }

    private fun showBottomSheet(){
        val bottomSheet = SettingsBottomSheet()
        bottomSheet.show(childFragmentManager, SettingsBottomSheet.TAG)
    }

    override fun onSettingsSelected(settings: Settings) {
        val bottomSheet = SettingsBottomSheet(settings)
        bottomSheet.show(childFragmentManager, SettingsBottomSheet.TAG)
    }

    override fun onSettingsLongPress(settings: Settings): Boolean {
        val doesExist = viewModel.settingsRepository.isSettingInUse(settings_id = settings.settings_id)
        var toastText = ""
        if(!doesExist){
            viewModel.delete(settings)
            toastText = "Setting deleted"
        }else{
            toastText = "Setting is in use. Cant delete!"

        }
        Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()

        return true
    }
}