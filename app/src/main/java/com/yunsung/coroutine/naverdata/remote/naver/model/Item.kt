package com.yunsung.coroutine.naverdata.remote.naver.model


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("description")
    val description: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("originallink")
    val originallink: String,
    @SerializedName("pubDate")
    val pubDate: String,
    @SerializedName("title")
    val title: String
)