package com.yunsung.coroutine.data.naverdata.local

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface NaverDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(naverNewsEntity: naverNewsEntity)


}