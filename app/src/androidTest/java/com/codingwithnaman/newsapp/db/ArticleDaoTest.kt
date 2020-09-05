package com.codingwithnaman.newsapp.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.codingwithnaman.newsapp.util.Utils
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArticleDaoTest {

    private lateinit var database: ArticleDatabase
    private lateinit var articleDao: ArticleDao

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
            ArticleDatabase::class.java).allowMainThreadQueries().build()
        articleDao = database.getArticleDao()
    }

    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun insertArticleList() =  runBlocking {
        val articleList = Utils.getCacheArticleList()
        articleDao.upsert(articleList)
        val fetchedArticleList = articleDao.getAllArticles()
        assert(fetchedArticleList.isNotEmpty())

    }
}