package com.example.news.ui.APIs

data class PostDataClass (
    val status: String,
    val totalResults: Short,
    val articles: ArrayList<PostComponents> = arrayListOf(),
    )