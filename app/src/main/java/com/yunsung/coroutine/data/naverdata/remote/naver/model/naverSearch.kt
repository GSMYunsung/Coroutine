package com.yunsung.coroutine.data.naverdata.remote.naver.model


import com.google.gson.annotations.SerializedName

data class naverSearch(
    @SerializedName("display")
    val display: Int,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("lastBuildDate")
    val lastBuildDate: String,
    @SerializedName("start")
    val start: Int,
    @SerializedName("total")
    val total: Int
)