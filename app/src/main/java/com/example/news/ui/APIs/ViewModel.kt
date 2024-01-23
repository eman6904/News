package com.example.news.ui.APIs

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModel:ViewModel(){
    val sourcesList = MutableLiveData<SourcesDataClass>()
    val postsList = MutableLiveData<com.example.news.ui.APIs.PostDataClass>()
    val postsListOfResult= MutableLiveData<com.example.news.ui.APIs.PostDataClass>()
    fun getSources(articleName:String): LiveData<SourcesDataClass> {
        ImplementationClass().getSources(articleName)
            .enqueue(object : Callback<SourcesDataClass> {
                override fun onResponse(call: Call<SourcesDataClass>, response: Response<SourcesDataClass>) {
                    sourcesList.postValue(response.body())
                }
                override fun onFailure(call: Call<SourcesDataClass>, t: Throwable) {
                    Log.d("errror", t.message.toString())
                }
            })
        return sourcesList
    }
    fun getPosts(id:String): LiveData<com.example.news.ui.APIs.PostDataClass> {
        ImplementationClass().getArticles(id)
            .enqueue(object :Callback<com.example.news.ui.APIs.PostDataClass>{
                override fun onResponse(call: Call<com.example.news.ui.APIs.PostDataClass>, response: Response<com.example.news.ui.APIs.PostDataClass>) {
                    postsList.postValue(response.body())
                }

                override fun onFailure(call: Call<com.example.news.ui.APIs.PostDataClass>, t: Throwable) {
                    Log.d("errror",t.message.toString())
                }
            })
        return postsList
    }
    fun getResult(endPoint:String): LiveData<com.example.news.ui.APIs.PostDataClass> {
        ImplementationClass().getResultsOfSearch(endPoint)
            .enqueue(object :Callback<com.example.news.ui.APIs.PostDataClass>{
                override fun onResponse(call: Call<com.example.news.ui.APIs.PostDataClass>, response: Response<com.example.news.ui.APIs.PostDataClass>) {
                    postsListOfResult.postValue(response.body())
                }

                override fun onFailure(call: Call<com.example.news.ui.APIs.PostDataClass>, t: Throwable) {
                    Log.d("errror",t.message.toString())
                }
            })
        return postsListOfResult
    }
}