package com.yunsung.coroutine.data.naverdata.local

import android.util.Log
import com.yunsung.coroutine.data.naverdata.remote.naver.NaverSearchDataSource
import com.yunsung.coroutine.data.naverdata.remote.naver.model.Item
import com.yunsung.coroutine.util.Utils
import javax.inject.Inject

class NaverRepository @Inject constructor(
    private val remote : NaverSearchDataSource,
    private val local : NaverLocalDataSource
) {

    suspend fun getNaver(item : Item,searchKeyword : String) : naverNewsEntity?{

        val localNaverResult = local.getNaver(
            naverNewsTitle = item.title,
            naverNewsContent = item.description,
            naverNewsUrl = item.link)

        return if(localNaverResult == null){
            requestAndSaveNaver(searchKeyword)
        }
        else{
            localNaverResult
        }
    }

    private suspend fun requestAndSaveNaver(searchKeyword : String): naverNewsEntity? {

        val queries = HashMap<String,String>()

        queries[Utils.Query_QUERY] = searchKeyword
        queries[Utils.QUERY_DISPLAY] = "10"
        queries[Utils.QUERY_START] = "1"
        queries[Utils.QUERY_SORT] = "sim"

        val headerQueries = HashMap<String,String>()

        headerQueries[Utils.HEADER_CLIENT_ID] = Utils.clientID
        headerQueries[Utils.HEADER_CLIENT_SECRET] = Utils.clientSecret

        try{

            val response = remote.getNaverSearchData(headerQueries,queries)

            if(response.isSuccessful && response.body() != null){
                val naverEntity = naverNewsEntity(
                    naverNewsTitle = response.body()!!.items[0].title,
                    naverNewsContent = response.body()!!.items[0].description,
                    naverLink = response.body()!!.items[0].link)

                local.insertNaver(naverEntity)
            }
            else{
                Log.d("NoResult", "requestAndSaveMeal: 저장 실패: ${response.message()}")
            }
        }
        catch (e : Exception){
            Log.d("NoResult", "requestAndSaveMeal: 저장 실패: ${e.stackTraceToString()}")
        }
        return null

    }

}