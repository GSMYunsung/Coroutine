package com.yunsung.coroutine.data.naverdata.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [naverNewsEntity::class], version = 1)
abstract class NaverDatabase : RoomDatabase() {
    abstract fun naverDao() : NaverDao
}