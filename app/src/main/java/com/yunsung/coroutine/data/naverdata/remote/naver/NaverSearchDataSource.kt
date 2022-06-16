package com.yunsung.coroutine.data.naverdata.remote.naver

import com.yunsung.coroutine.data.naverdata.remote.naver.model.naverSearch
import retrofit2.Response
import javax.inject.Inject

class NaverSearchDataSource @Inject constructor(
    private val naverSearchApi : NaverSearchService
) {

    suspend fun getNaverSearchData(headerQuery : HashMap<String,String>,query : HashMap<String,String>) : Response<naverSearch> =
        naverSearchApi.getNaverSearchData(headerQuery, query)

}