package com.example.news.ui.APIs

import com.example.news.ui.ui.HomeFragment
import retrofit2.http.GET
import retrofit2.http.Query

interface Interface {
    @GET("sources")
    fun getSources(@Query("category")category:String,@Query("apiKey")apiKey:String,@Query("language")language:String= HomeFragment.language):retrofit2.Call<SourcesDataClass>
    @GET("everything")
    fun getArticles(@Query("sources")category:String?,@Query("apiKey")apiKey:String):retrofit2.Call<PostDataClass>
    @GET("everything")
    fun getResultsOfSearch(@Query("apiKey")apiKey:String,@Query("sortBy")sortBy:String,@Query("q")q:String):retrofit2.Call<PostDataClass>
}
//@GET("sources")
//sources==>called endPoint
//fun getSources(@Query("category")category:String,@Query("apiKey")apiKey:String,@Query("language")language:String= Home.language)
//هات كل المصادر اللي القسم فيها (رياضيات مثلا) والapikey بكذا ,للغة بكذا
//لاحظ ان الapikey تستخدم ف كل الqueries