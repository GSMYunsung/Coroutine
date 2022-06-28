package com.yunsung.coroutine.data.naverdata.local

import androidx.room.*

@Dao
interface NaverDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(naverNewsEntity: naverNewsEntity)

    @Query("SELECT * FROM naverNewsEntity WHERE  keyword = :keyword")
    suspend fun getNews(keyword : String) : List<naverNewsEntity>

    @Delete
    suspend fun deleteNews (newsEntity: naverNewsEntity)


}