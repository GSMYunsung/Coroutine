package com.yunsung.coroutine.artdata.remote.artgallery.model


import com.google.gson.annotations.SerializedName

data class ArtGallery(
    @SerializedName("header")
    val header: Header,
    @SerializedName("MsmArtGlrBaseInfo")
    val msmArtGlrBaseInfo: List<MsmArtGlrBaseInfo>
)