package com.example.news.ui.APIs

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SourcesViewModel:ViewModel(){
    val sourcesList = MutableLiveData<Sources>()
    val postsList = MutableLiveData<com.example.news.ui.APIs.Posts>()
    val postsListOfResult= MutableLiveData<com.example.news.ui.APIs.Posts>()
    inner class sources(var articleName:String) {
        fun getSources(): LiveData<Sources> {
            ImplementationClass().getSources(articleName)
                .enqueue(object : Callback<Sources> {
                    override fun onResponse(call: Call<Sources>, response: Response<Sources>) {
                        sourcesList.postValue(response.body())
                    }

                    override fun onFailure(call: Call<Sources>, t: Throwable) {
                        Log.d("errror", t.message.toString())
                    }
                })
            return sourcesList
        }
    }
   inner class Posts(var sourceId:String)
   {
       fun getPosts(): LiveData<com.example.news.ui.APIs.Posts> {
           ImplementationClass().getArticles(sourceId)
               .enqueue(object :Callback<com.example.news.ui.APIs.Posts>{
                   override fun onResponse(call: Call<com.example.news.ui.APIs.Posts>, response: Response<com.example.news.ui.APIs.Posts>) {
                       postsList.postValue(response.body())
                   }

                   override fun onFailure(call: Call<com.example.news.ui.APIs.Posts>, t: Throwable) {
                       Log.d("errror",t.message.toString())
                   }
               })
           return postsList
       }
   }
    inner class Results(var q:String)
    {
        fun getResult(): LiveData<com.example.news.ui.APIs.Posts> {
            ImplementationClass().getResultsOfSearch(q)
                .enqueue(object :Callback<com.example.news.ui.APIs.Posts>{
                    override fun onResponse(call: Call<com.example.news.ui.APIs.Posts>, response: Response<com.example.news.ui.APIs.Posts>) {
                        postsListOfResult.postValue(response.body())
                    }

                    override fun onFailure(call: Call<com.example.news.ui.APIs.Posts>, t: Throwable) {
                        Log.d("errror",t.message.toString())
                    }
                })
            return postsListOfResult
        }
    }
}