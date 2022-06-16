package com.yunsung.coroutine

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yunsung.coroutine.data.naverdata.remote.naver.NaverSearchDataSource
import com.yunsung.coroutine.data.naverdata.remote.naver.model.Item
import com.yunsung.coroutine.util.NetWorkResult
import com.yunsung.coroutine.util.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NaverSearchViewModel @Inject constructor(
    private val naverSearchDataSource : NaverSearchDataSource
) : ViewModel() {

    private val _naverSearchDataList = MutableLiveData<NetWorkResult<List<Item>>>()
    val naverSearchDataList : LiveData<NetWorkResult<List<Item>>> get() =_naverSearchDataList

    fun getNaverSearchData() = viewModelScope.launch {

        _naverSearchDataList.value = NetWorkResult.Loading()

        val queries = HashMap<String,String>()

        queries[Utils.Query_QUERY] = "김치"
        queries[Utils.QUERY_DISPLAY] = "10"
        queries[Utils.QUERY_START] = "1"
        queries[Utils.QUERY_SORT] = "sim"

        val headerQueries = HashMap<String,String>()

//        queries[Utils.HEADER_CLIENT_ID] = Utils.clientID
//        queries[Utils.HEADER_CLIENT_SECRET] = Utils.clientSecret

        headerQueries[Utils.HEADER_CLIENT_ID] = Utils.clientID
        headerQueries[Utils.HEADER_CLIENT_SECRET] = Utils.clientSecret
            _naverSearchDataList.value = try {

                val response = naverSearchDataSource.getNaverSearchData(headerQuery = headerQueries, query = queries)

                if (response.isSuccessful && response.body() != null) {

                    val naverResponse = response.body()!!

                    if (naverResponse.items.isNotEmpty())
                        NetWorkResult.Success(naverResponse.items)

                    else {
                        NetWorkResult.Error("errorString")

                    }

                } else {
                    NetWorkResult.Error(response.message())
                }
            } catch (e: Exception) {
                NetWorkResult.Error(e.stackTraceToString())
            }

    }

}