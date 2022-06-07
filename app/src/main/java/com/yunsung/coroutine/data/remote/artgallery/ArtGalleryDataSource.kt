package com.yunsung.coroutine.data.remote.artgallery

import com.yunsung.coroutine.data.remote.artgallery.model.ArtGallery
import com.yunsung.coroutine.util.Utils.apiKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class ArtGalleryDataSource @Inject constructor(
    private val artGalleryService : ArtGalleryService
) {

    // flow

//    suspend fun getArtGallery(queries : HashMap<String, String>) : Flow<ArtGallery> {
//        return flow {
//            emit(artGalleryService.getArtGallery(queries))
//        }
//    }

    // coroutine

    suspend fun getArtGallery(queries : HashMap<String, String>) : Response<ArtGallery> = artGalleryService.getArtGallery(
        apiKey,queries)
}