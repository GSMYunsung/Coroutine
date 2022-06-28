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

    suspend fun getNaver(searchKeyword: String) : List<naverNewsEntity>? {

        val localNaverResult : List<naverNewsEntity> = local.getNaver(
            keyword = searchKeyword)

        Log.d("localNaverResult",localNaverResult.toString())

        return if(localNaverResult == null){
            requestAndSaveNaver(searchKeyword)
        }
        else{
            localNaverResult
        }
    }

    private suspend fun requestAndSaveNaver(searchKeyword : String): List<naverNewsEntity>? {

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

                for(index in 0 until response.body()!!.items.size)
                {
                    val naverEntity = naverNewsEntity(
                        naverNewsTitle = response.body()!!.items[index].title,
                        naverNewsContent = response.body()!!.items[index].description,
                        naverLink = response.body()!!.items[index].link,
                        keyWord = searchKeyword)

                    local.insertNaver(naverEntity)
                }
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