package com.appcosmetics.app.data.repository

import android.app.DownloadManager
import com.appcosmetics.app.data.model.ActionState
import com.appcosmetics.app.data.model.News
import com.appcosmetics.app.data.remote.NewsService
import com.appcosmetics.app.data.remote.RetrofitAPi
import retrofit2.await
import retrofit2.http.Query
import java.lang.Exception

class NewsRepository {
    private val newsService: NewsService by lazy { RetrofitAPi.newsService() }

    suspend fun listNews() : ActionState<List<News>> {
        return try {
            val list = newsService.listNews().await()
            ActionState(list.data)
        } catch (e: Exception) {
            ActionState(message = e.message, isSuccess = false)
        }
    }

    suspend fun detailNews(url: String) : ActionState<News> {
        return try {
            val list = newsService.detailNews(url).await()
            ActionState(list.data.first())
        } catch (e: Exception) {
            ActionState(message = e.message, isSuccess = false)
        }
    }

    suspend fun searchNews(query: String) : ActionState<List<News>> {
        return try {
            val list = newsService.searchNews(query).await()
            ActionState(list.data)
        } catch (e: Exception) {
            ActionState(message = e.message, isSuccess = false)
        }
    }
}