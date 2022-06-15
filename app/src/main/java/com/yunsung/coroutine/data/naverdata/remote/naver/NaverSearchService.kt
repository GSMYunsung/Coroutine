package com.yunsung.coroutine.data.naverdata.remote.naver

import com.yunsung.coroutine.data.naverdata.remote.naver.model.naverSearch
import com.yunsung.coroutine.util.Utils
import com.yunsung.coroutine.util.Utils.HEADER_CLIENT_ID
import com.yunsung.coroutine.util.Utils.HEADER_CLIENT_SECRET
import com.yunsung.coroutine.util.Utils.clientID
import com.yunsung.coroutine.util.Utils.clientSecret
import retrofit2.Response
import retrofit2.http.*

interface NaverSearchService {

    @GET("news.json")
    suspend fun getNaverSearchData(
       @HeaderMap headers: Map<String, String>,
        @QueryMap queries : Map<String, String>
    ) : Response<naverSearch>

}