package com.example.test.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.example.test.data.models.ClassifiedItem
import com.example.test.databinding.FragmentMainBinding
import com.example.test.home.adapters.ClassifiedItemsAdapter
import com.example.test.home.vm.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.layout_no_item_found.view.*


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRendering()
        setupListener()
        viewModel.fetchData()
    }

    private fun initRendering() = with(viewModel) {
        classifiedItemsLiveData.observe(viewLifecycleOwner, ::setupRecyclerView)
        loadingLiveData.observe(viewLifecycleOwner, ::renderSchimmerLayout)
        noItemFoundLiveData.observe(viewLifecycleOwner, ::renderNoItemFounud)
    }

    private fun setupRecyclerView(list : List<ClassifiedItem>) = with(binding) {
        rvRepos.visibility = View.VISIBLE
        swiperefresh.isRefreshing = false
        val adapter = ClassifiedItemsAdapter(list)
        rvRepos.adapter = adapter
    }

    private fun renderSchimmerLayout(isShown: Boolean) {
        binding.shimmerLayout.isVisible = isShown
    }

    private fun renderNoItemFounud(isShown: Boolean) {
        binding.noItemFound.isVisible = isShown
    }

    private fun setupListener() = with(binding) {
        swiperefresh.setOnRefreshListener {
            viewModel.fetchData()
        }
       binding.noItemFound.btnRetry.setOnClickListener {
           binding.noItemFound.isVisible = false
           viewModel.fetchData()
       }

    }
}