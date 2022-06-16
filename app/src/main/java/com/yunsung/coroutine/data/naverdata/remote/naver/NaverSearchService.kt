package com.yunsung.coroutine.data.naverdata.remote.naver

import com.yunsung.coroutine.data.naverdata.remote.naver.model.naverSearch
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.QueryMap

interface NaverSearchService {

    @GET("news.json")
    suspend fun getNaverSearchData(
       @HeaderMap headers: Map<String, String>,
        @QueryMap queries : Map<String, String>
    ) : Response<naverSearch>

}