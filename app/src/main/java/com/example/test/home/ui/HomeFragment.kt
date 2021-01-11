package com.example.test.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.data.models.ClassifiedItem
import com.example.test.databinding.FragmentMainBinding
import com.example.test.home.adapters.GitHubRepoAdapter
import com.example.test.home.vm.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*


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
        viewModel.fetchData(true)
    }

    private fun setupRecyclerView(list : List<ClassifiedItem>) {
        rvRepos.visibility = View.VISIBLE
        swiperefresh.isRefreshing = false
        val adapter = GitHubRepoAdapter(list)
        rvRepos.addItemDecoration(
            DividerItemDecoration(
                rvRepos.context,
                DividerItemDecoration.VERTICAL
            )
        )

        rvRepos.adapter = adapter
        rvRepos.layoutManager = LinearLayoutManager(context)
    }

    private fun setupClickListener() {

        swiperefresh.setOnRefreshListener {
            viewModel.fetchData(true)
        }
    }
}