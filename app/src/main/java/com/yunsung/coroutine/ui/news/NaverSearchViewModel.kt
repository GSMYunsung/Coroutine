package com.yunsung.coroutine.ui.news

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yunsung.coroutine.data.naverdata.local.NaverRepository
import com.yunsung.coroutine.data.naverdata.local.naverNewsEntity
import com.yunsung.coroutine.data.naverdata.remote.naver.NaverSearchDataSource
import com.yunsung.coroutine.data.naverdata.remote.naver.model.Item
import com.yunsung.coroutine.util.NetWorkResult
import com.yunsung.coroutine.util.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

// 연결

class NaverSearchViewModel @Inject constructor(
    private val naverSearchDataSource : NaverSearchDataSource,
    private val naverRepository: NaverRepository
) : ViewModel() {

    private val _naverSearchDataList = MutableLiveData<NetWorkResult<List<Item>>>()
    val naverSearchDataList : LiveData<NetWorkResult<List<Item>>> get() =_naverSearchDataList

    private val _naverRoomData = MutableLiveData<List<Item>>()
    val naverRoomData : LiveData<List<Item>> get() =_naverRoomData

    val searchQuery = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val searchQueryResult = searchQuery
        .debounce(500)
        .filter { it.isNotEmpty() }
        .distinctUntilChanged()
        .flatMapLatest { keyword ->
            flow{
                getNaverNews(keyword)
                emit(keyword)
            }
        }

    fun getNaverNews(keyword : String){

        viewModelScope.launch {

            val naverEntity : List<naverNewsEntity> = naverRepository.getNaver(keyword) ?: return@launch

            Log.d("cocopam12535",naverEntity.toString())

//            _naverRoomData.value = List<Item>(
//                naverEntity.naverNewsTitle,
//                naverEntity.naverNewsContent,
//                naverEntity.naverLink,
//            )
        }

    }



    fun getNaverSearchData(searchKeyword : String) = viewModelScope.launch {

        _naverSearchDataList.value = NetWorkResult.Loading()

        val queries = HashMap<String,String>()

        queries[Utils.Query_QUERY] = searchKeyword
        queries[Utils.QUERY_DISPLAY] = "10"
        queries[Utils.QUERY_START] = "1"
        queries[Utils.QUERY_SORT] = "sim"

        val headerQueries = HashMap<String,String>()

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