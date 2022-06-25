package com.example.flashcards.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.flashcards.R
import com.example.flashcards.adapters.CategoryAdapter
import com.example.flashcards.databinding.FragmentCategoryListBinding
import com.example.flashcards.models.Category
import com.example.flashcards.ui.categories.category_add.CategoryBottomSheet
import com.example.flashcards.ui.deck_list.CategoryListViewModel
import com.google.android.material.appbar.MaterialToolbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryListFragment : Fragment(), OnCategoryEventListener {

    private lateinit var categoryAdapter: CategoryAdapter
    private val viewModel: CategoryListViewModel by viewModel()
    private lateinit var binding: FragmentCategoryListBinding

    private lateinit var toolbar: MaterialToolbar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryListBinding.inflate(layoutInflater)

        binding.addNewCategoryBtn.setOnClickListener{
            showBottomSheet()
        }

        setupRecyclerView()

        viewModel.categories.observe(viewLifecycleOwner) {
            if (it != null && it.isNotEmpty()){
                categoryAdapter.setCategories(it)
            }
        }

        toolbar = requireActivity().findViewById(R.id.toolbar)
        toolbar.title = "My Categories"

        return binding.root
    }

    private fun setupRecyclerView(){
        binding.categoryListRvCategories.layoutManager = GridLayoutManager(
            context,
            2
        )

        categoryAdapter = CategoryAdapter()
        categoryAdapter.onCategorySelectedListener = this
        binding.categoryListRvCategories.adapter = categoryAdapter

    }

    private fun showBottomSheet(){
        val bottomSheet = CategoryBottomSheet()
        bottomSheet.show(childFragmentManager, CategoryBottomSheet.TAG)
    }

    override fun onCategorySelected(category: Category) {
        val bottomSheet = CategoryBottomSheet(category)
        bottomSheet.show(childFragmentManager, CategoryBottomSheet.TAG)
    }

    override fun onCategoryLongPress(category: Category): Boolean {
        val isInUse = viewModel.categoryRepository.isCategoryInUse(category.category_id)
        var toastText = ""

        if(!isInUse){
            viewModel.delete(category)
            toastText = "Category deleted"
        }else{
            toastText = "Category is in use. Cant delete!"
        }

        Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
        return true
    }

}