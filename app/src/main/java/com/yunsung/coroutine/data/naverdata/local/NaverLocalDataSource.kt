package com.yunsung.coroutine.data.naverdata.local

import javax.inject.Inject

class NaverLocalDataSource @Inject constructor(
    private val naverDao : NaverDao
) {
    suspend fun insertNaver(naverNewsEntity: naverNewsEntity) = naverDao.insertNews(naverNewsEntity)

    suspend fun deleteMeal(naverNewsEntity: naverNewsEntity) = naverDao.deleteNews(naverNewsEntity)

    suspend fun getNaver(naverNewsTitle : String, naverNewsContent : String, naverNewsUrl : String) = naverDao.getNews(naverNewsTitle, naverNewsContent, naverNewsUrl)
}