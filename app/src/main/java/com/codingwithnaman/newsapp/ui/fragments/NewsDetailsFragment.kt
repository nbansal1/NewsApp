package com.codingwithnaman.newsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.codingwithnaman.newsapp.R
import com.codingwithnaman.newsapp.model.Article
import kotlinx.android.synthetic.main.fragment_news_details.*

class NewsDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val bundle = arguments
        val article: Article = bundle?.getSerializable("article") as Article

        setUI(view, article)

        custom_back.setOnClickListener {
            findNavController().navigateUp()
        }

    }

    private fun setUI(view: View, article: Article) {
        Glide.with(view)
            .load(article.urlToImage)
            .centerCrop()
            .into(ivHeadlinesPoster)

        tvNewsDescription.text = article.description
        tvHeadingTitle.text = article.title
        tvHeadingPublisher.text = article.author
        tvHeadingDate.text = article.publishedAt
    }
}