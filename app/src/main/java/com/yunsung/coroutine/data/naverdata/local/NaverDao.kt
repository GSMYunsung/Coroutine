package com.yunsung.coroutine.data.naverdata.local

import androidx.room.*

@Dao
interface NaverDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(naverNewsEntity: naverNewsEntity)

    @Query("SELECT * FROM naverNewsEntity WHERE naverNewsTitle = :naverNewsTitle AND naverNewsContent = :naverNewsContent ANd naverLink = :naverLink")
    suspend fun getNews(naverNewsTitle : String, naverNewsContent : String, naverLink : String) : naverNewsEntity

    @Delete
    suspend fun deleteNews (newsEntity: naverNewsEntity)


}