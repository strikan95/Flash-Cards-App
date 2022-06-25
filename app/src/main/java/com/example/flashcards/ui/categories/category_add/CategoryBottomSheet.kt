package com.example.flashcards.ui.categories.category_add

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.example.flashcards.R
import com.example.flashcards.databinding.BottomSheetFragmentCategoryListBinding
import com.example.flashcards.models.Category
import com.example.flashcards.utils.Utils
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rtugeek.android.colorseekbar.thumb.DefaultThumbDrawer
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryBottomSheet(val category: Category? = null) : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetFragmentCategoryListBinding
    private val viewModel: CategoryBottomSheetViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetFragmentCategoryListBinding.inflate(layoutInflater)

        binding.categoryNameInputBox.addTextChangedListener {
            viewModel.categoryName = it.toString()
            binding.categoryNameInputBox.error = null
        }


        binding.createDeckBtn.setOnClickListener{
            saveCategory()
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.colorSeekBar.color = category?.category_color ?: -65535
        binding.categoryNameInputBox.setText(category?.category_name ?: "")
        binding.createDeckBtn.text = if(category == null) "Save" else "Update"



        binding.colorSeekBar.barHeight = 16
        val thumbDrawer = DefaultThumbDrawer(
            Utils.dp2px(requireContext(), 16F), Color.WHITE, Color.BLACK
        )
        thumbDrawer.ringBorderSize = 1
        thumbDrawer.ringSize = 5
        binding.colorSeekBar.thumbDrawer = thumbDrawer
        binding.colorCircleDisplay.background.setTint(binding.colorSeekBar.color)
        binding.colorSeekBar.setOnColorChangeListener { progress, color ->
            binding.colorCircleDisplay.background.setTint(binding.colorSeekBar.color)
            Log.d(TAG, "onViewCreated: " + binding.colorSeekBar.color)
        }
    }

    private fun saveCategory(){
        if (validateInput()){
            if (category == null){
                viewModel.save(binding.colorSeekBar.color)
            }else{
                category.category_name = viewModel.categoryName
                category.category_color = binding.colorSeekBar.color
                viewModel.update(category)
            }

            val toastText = if (category == null){
                "Category saved!"
            } else {
                "Category updated!"
            }
            dismiss()
            Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateInput(): Boolean{
        var isValidated: Boolean = true

        if (viewModel.categoryName == ""){
            binding.categoryNameInputBox.error = "Field cannot be empty"
            isValidated = false
        }

        return isValidated
    }


    companion object{
        const val TAG = "CategoryBottomSheet"
    }
}