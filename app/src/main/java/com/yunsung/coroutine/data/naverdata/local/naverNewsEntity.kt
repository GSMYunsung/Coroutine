package com.yunsung.coroutine.data.naverdata.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class naverNewsEntity(
    var naverNewsTitle : String,
    var naverNewsContent : String,
    var naverLink : String
){
    @PrimaryKey(autoGenerate = true)
    var id = 0
}