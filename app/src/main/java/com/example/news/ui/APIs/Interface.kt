package com.example.news.ui.APIs

import com.example.news.ui.ui.Home
import retrofit2.http.GET
import retrofit2.http.Query

interface Interface {
    @GET("sources")
    fun getSources(@Query("category")category:String,@Query("apiKey")apiKey:String,@Query("language")language:String= Home.language):retrofit2.Call<Sources>
    @GET("everything")
    fun getArticles(@Query("sources")category:String?,@Query("apiKey")apiKey:String):retrofit2.Call<Posts>
    @GET("everything")
    fun getResultsOfSearch(@Query("apiKey")apiKey:String,@Query("sortBy")sortBy:String,@Query("q")q:String):retrofit2.Call<Posts>
}