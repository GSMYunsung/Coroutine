package com.yunsung.coroutine.artdata.remote.artgallery

import com.yunsung.coroutine.artdata.remote.artgallery.model.ArtGallery
import retrofit2.Response
import retrofit2.http.*

interface ArtGalleryService {

    @GET("getmsmartglrbaseinfo")
    suspend fun getArtGallery(
        @Query (encoded = true , value = "serviceKey") serviceKey : String = "u4AoHlpli2eYsQ7hGGzjoWO4Im9iktqI8f2OT6Yzl4d3cHAAoaWXBaXxrhfdAp8ESXDn7%2BuL8KB1JcKqEZsY1g%3D%3D",
        @QueryMap queries : Map<String, String>
    ) : Response<ArtGallery>

//        @GET("artGalleryInfo")
//        suspend fun getArtGallery(
//            @QueryMap queries : Map<String, String>
//        ) : ArtGallery

}