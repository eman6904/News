package com.example.news.ui.APIs

import com.google.gson.annotations.SerializedName


data class Sources (
     var status  : String?= null,
     var sources : ArrayList<SourceComponents> = arrayListOf()
)