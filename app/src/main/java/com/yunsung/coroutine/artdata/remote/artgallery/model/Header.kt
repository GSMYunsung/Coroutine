package com.yunsung.coroutine.artdata.remote.artgallery.model


import com.google.gson.annotations.SerializedName

data class Header(
    @SerializedName("numOfRows")
    val numOfRows: Int,
    @SerializedName("pageNo")
    val pageNo: Int,
    @SerializedName("resultCode")
    val resultCode: String,
    @SerializedName("resultMsg")
    val resultMsg: String,
    @SerializedName("totalCount")
    val totalCount: Int,
    @SerializedName("totalPage")
    val totalPage: Int
)