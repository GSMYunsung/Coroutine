package com.yunsung.coroutine.naverdata.remote.naver

import com.yunsung.coroutine.naverdata.remote.naver.model.naverSearch
import retrofit2.Response
import retrofit2.http.QueryMap
import javax.inject.Inject

class NaverSearchDataSource @Inject constructor(
    private val naverSearchApi : NaverSearchService
) {

    suspend fun getNaverSearchData(headerQuery : HashMap<String,String>,query : HashMap<String,String>) : Response<naverSearch> =
        naverSearchApi.getNaverSearchData(headerQuery,query)

}