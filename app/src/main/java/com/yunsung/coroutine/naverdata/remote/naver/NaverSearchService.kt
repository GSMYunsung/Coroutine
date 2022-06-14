package com.yunsung.coroutine.naverdata.remote.naver

import com.yunsung.coroutine.artdata.remote.artgallery.model.ArtGallery
import com.yunsung.coroutine.naverdata.remote.naver.model.naverSearch
import retrofit2.Response
import retrofit2.http.*

interface NaverSearchService {

    @GET
    suspend fun getNaverSearchData(
        @HeaderMap header : Map<String,String>,
        @QueryMap queries : Map<String, String>
    ) : Response<naverSearch>

}