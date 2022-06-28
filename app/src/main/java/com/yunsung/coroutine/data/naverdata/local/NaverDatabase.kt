package com.yunsung.coroutine.data.naverdata.local

import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = arrayOf(naverNewsEntity::class), version = 2)
abstract class NaverDatabase : RoomDatabase() {
    abstract fun naverDao() : NaverDao
}