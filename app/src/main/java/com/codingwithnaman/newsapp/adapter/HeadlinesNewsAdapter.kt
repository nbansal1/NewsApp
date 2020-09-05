package com.codingwithnaman.newsapp.adapter

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codingwithnaman.newsapp.R
import com.codingwithnaman.newsapp.model.Article
import com.codingwithnaman.newsapp.util.Utils
import kotlinx.android.synthetic.main.headlines_item_layout.view.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class HeadlinesNewsAdapter(
    private val navController: NavController
) : RecyclerView.Adapter<HeadlinesNewsAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.headlines_item_layout,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this)
                .load(article.urlToImage)
                .centerCrop()
                .into(ivHeadlinesPoster)

            tvHeadlines.text = article.title
            tvHeadlinesChannel.text = article.source!!.name

            tvHeadlinesDate.text = Utils.parseDate(article.publishedAt)

            setOnClickListener {
                val bundle = Bundle().apply {
                    putSerializable("article",article)
                }
                navController.navigate(R.id.action_headlinesFragment_to_newsDetailsFragment,bundle)
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this@HeadlinesNewsAdapter,differCallback)

}