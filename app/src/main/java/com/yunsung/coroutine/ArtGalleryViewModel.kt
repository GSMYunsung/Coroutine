package com.yunsung.coroutine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yunsung.coroutine.artdata.remote.artgallery.ArtGalleryDataSource
import com.yunsung.coroutine.artdata.remote.artgallery.model.MsmArtGlrBaseInfo
import com.yunsung.coroutine.util.NetWorkResult
import com.yunsung.coroutine.util.Utils.QUERY_NUM_OF_ROWS
import com.yunsung.coroutine.util.Utils.QUERY_OPERATION_RULE_NUM
import com.yunsung.coroutine.util.Utils.QUERY_PAGE_NO
import com.yunsung.coroutine.util.Utils.QUERY_TYPE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtGalleryViewModel @Inject constructor(
    private val artGalleryDataSource : ArtGalleryDataSource
) : ViewModel() {

    private val _artGalleryList = MutableLiveData<NetWorkResult<List<MsmArtGlrBaseInfo>>>()
    val artGalleryList: LiveData<NetWorkResult<List<MsmArtGlrBaseInfo>>> get() = _artGalleryList

    fun getArtGalleryList() = viewModelScope.launch {

        _artGalleryList.value = NetWorkResult.Loading()

        val queries = HashMap<String, String>()

        //queries[QUERY_API_KEY] = apiKey
        queries[QUERY_NUM_OF_ROWS] = "20"
        queries[QUERY_PAGE_NO] = "1"
        queries[QUERY_TYPE] = "json"
        queries[QUERY_OPERATION_RULE_NUM] = "공립"

        _artGalleryList.value = try {

            val response = artGalleryDataSource.getArtGallery(queries)

            if (response.isSuccessful && response.body() != null) {
                val artGalleryResponse = response.body()!!
                val header = artGalleryResponse.header

                if (header.resultCode == "00")
                    NetWorkResult.Success(artGalleryResponse.msmArtGlrBaseInfo)
                else {
                    val errorString = "${header.resultCode}: ${header.resultMsg}"
                    NetWorkResult.Error(errorString)
                }
            } else {
                NetWorkResult.Error(response.message())
            }
        } catch (e: Exception) {
            NetWorkResult.Error(e.stackTraceToString())
        }
    }
}