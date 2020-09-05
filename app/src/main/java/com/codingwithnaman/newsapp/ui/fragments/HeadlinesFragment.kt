package com.codingwithnaman.newsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codingwithnaman.newsapp.R
import com.codingwithnaman.newsapp.adapter.HeadlinesNewsAdapter
import com.codingwithnaman.newsapp.ui.NewsActivity
import com.codingwithnaman.newsapp.ui.NewsViewModel
import com.codingwithnaman.newsapp.util.Constant.Companion.QUERY_PAGE_SIZE
import com.codingwithnaman.newsapp.util.Resource
import kotlinx.android.synthetic.main.fragment_headline.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HeadlinesFragment : Fragment() {
    private val viewModel by viewModel<NewsViewModel>()
    lateinit var headlinesNewsAdapter: HeadlinesNewsAdapter
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_headline, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        setRecyclerView()

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success -> {
                    it.data?.let {newsResponse ->
                        progress_circular.visibility = View.GONE
                        isLoading = false
                        headlinesNewsAdapter.differ.submitList(newsResponse.articles.toList())
                        val totalPages = newsResponse.totalResults / QUERY_PAGE_SIZE + 2
                        isLastPage = viewModel.breakingNewsPage == totalPages
                        if(isLastPage) {
                            rvHeadlines.setPadding(0, 0, 0, 0)
                        }
                    }
                }
                is Resource.Loading -> {
                    progress_circular.visibility = View.VISIBLE
                    isLoading = true
                }
                is Resource.Error -> {
                    progress_circular.visibility = View.GONE
                    isLoading = false
                    it.message?.let {msg ->
                        Toast.makeText(activity, "An error occured: $msg", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })

        if(!viewModel.hasInternetConnection()){
            viewModel.offlineBreakingNews.observe(viewLifecycleOwner, Observer {
                headlinesNewsAdapter.differ.submitList(it)
                progress_circular.visibility = View.GONE
            })
        }
    }


    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning &&
                    isTotalMoreThanVisible && isScrolling
            if(shouldPaginate) {
                viewModel.getBreakingNews("us")
                isScrolling = false
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }
    }

    private fun setRecyclerView(){
        headlinesNewsAdapter = HeadlinesNewsAdapter(navController)
        rvHeadlines.apply {
            adapter = headlinesNewsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@HeadlinesFragment.scrollListener)
        }
    }
}