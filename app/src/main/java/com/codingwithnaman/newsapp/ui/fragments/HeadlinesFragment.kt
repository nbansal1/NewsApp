package com.codingwithnaman.newsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingwithnaman.newsapp.R
import com.codingwithnaman.newsapp.adapter.HeadlinesNewsAdapter
import com.codingwithnaman.newsapp.ui.NewsActivity
import com.codingwithnaman.newsapp.ui.NewsViewModel
import com.codingwithnaman.newsapp.util.Resource
import kotlinx.android.synthetic.main.fragment_headline.*

class HeadlinesFragment : Fragment() {
    lateinit var viewModel : NewsViewModel
    lateinit var headlinesNewsAdapter: HeadlinesNewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_headline, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as NewsActivity).viewModel

        setRecyclerView()

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success -> {
                    it.data?.let {newsResponse ->
                        headlinesNewsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
            }
        })

        viewModel.getBreakingNews("us")
    }

    private fun setRecyclerView(){
        headlinesNewsAdapter = HeadlinesNewsAdapter()
        rvHeadlines.apply {
            adapter = headlinesNewsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}