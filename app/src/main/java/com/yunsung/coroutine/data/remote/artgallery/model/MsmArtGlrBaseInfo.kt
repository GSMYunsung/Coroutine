package com.yunsung.coroutine.data.remote.artgallery.model


import com.google.gson.annotations.SerializedName

data class MsmArtGlrBaseInfo(
    @SerializedName("addrJibun")
    val addrJibun: String,
    @SerializedName("addrRoad")
    val addrRoad: String,
    @SerializedName("adultAdmsFee")
    val adultAdmsFee: String,
    @SerializedName("childAdmsFee")
    val childAdmsFee: String,
    @SerializedName("closeInfo")
    val closeInfo: String,
    @SerializedName("holidayBeginTime")
    val holidayBeginTime: String,
    @SerializedName("holidayEndTime")
    val holidayEndTime: String,
    @SerializedName("homePage")
    val homePage: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("mngAgcNm")
    val mngAgcNm: String,
    @SerializedName("mngAgcTel")
    val mngAgcTel: String,
    @SerializedName("msmArtGlrIntro")
    val msmArtGlrIntro: String,
    @SerializedName("msmArtGlrNm")
    val msmArtGlrNm: String,
    @SerializedName("operationAgcNm")
    val operationAgcNm: String,
    @SerializedName("operationAgcTel")
    val operationAgcTel: String,
    @SerializedName("operationRuleNm")
    val operationRuleNm: String,
    @SerializedName("syncTime")
    val syncTime: String,
    @SerializedName("trafficInfo")
    val trafficInfo: String,
    @SerializedName("weekdayBeginTime")
    val weekdayBeginTime: String,
    @SerializedName("weekdayEndTime")
    val weekdayEndTime: String,
    @SerializedName("youthAdmsFee")
    val youthAdmsFee: String
)