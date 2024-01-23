package com.example.news.ui.APIs

import com.google.gson.annotations.SerializedName


data class SourcesDataClass (
     var status  : String?= null,
     var sources : ArrayList<SourceComponents> = arrayListOf()
)