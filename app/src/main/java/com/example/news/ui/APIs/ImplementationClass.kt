package com.example.news.ui.APIs
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ImplementationClass {
    val postInterface: Interface

    constructor () {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        postInterface = retrofit.create(Interface::class.java)
    }

    fun getSources(articleName:String): retrofit2.Call<SourcesDataClass> {
        return postInterface.getSources(articleName,"d345bd7c207246288e20202fcd1aab03")
    }
    fun getArticles(sourceId:String): retrofit2.Call<PostDataClass> {
        return postInterface.getArticles(sourceId,"d345bd7c207246288e20202fcd1aab03")
    }
    fun getResultsOfSearch(q:String): retrofit2.Call<PostDataClass> {
        return postInterface.getResultsOfSearch("d345bd7c207246288e20202fcd1aab03","popularity",q)
    }
}