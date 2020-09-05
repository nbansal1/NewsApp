package com.codingwithnaman.newsapp.util

import android.os.Build
import androidx.annotation.RequiresApi
import com.codingwithnaman.newsapp.model.Article
import com.codingwithnaman.newsapp.model.Source
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Utils {

    @RequiresApi(Build.VERSION_CODES.O)
    fun parseDate(dateToFormat : String?) : String {
        val format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZZZZZ")
        val date = LocalDate.parse(dateToFormat, format)
        return date.toString()
    }

    fun getCacheArticleList() : List<Article> {
        val articleList: List<Article> = listOf(
            Article(null,"CNN","Hello welcome","Hey welcome to the breaking news",
        "1980-22-09", Source(0, "CNN"),"Headline","https://www.foxbusiness.com/markets/amazon-expanding-to-25000-workers-in-seattle-suburb-report",
                "https://a57.foxnews.com/static.foxbusiness.com/foxbusiness.com/content/uploads/2019/05/0/0/iStock-Amazon.jpg?ve=1&tl=1"))

        return articleList
    }
}