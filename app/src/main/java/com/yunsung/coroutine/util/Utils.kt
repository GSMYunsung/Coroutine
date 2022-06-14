package com.yunsung.coroutine.util

import retrofit2.http.Query

object Utils {
    const val BASE_URL_ARTGALLERY = "https://apis.data.go.kr/6290000/msmartglrbaseinfo/"
    const val BASE_URL_NAVERDEARCH = "https://openapi.naver.com/v1/search/news.json?"

    const val apiKey = "u4AoHlpli2eYsQ7hGGzjoWO4Im9iktqI8f2OT6Yzl4d3cHAAoaWXBaXxrhfdAp8ESXDn7%2BuL8KB1JcKqEZsY1g%3D%3D"

    // artApi

    const val QUERY_API_KEY = "serviceKey"
    const val QUERY_NUM_OF_ROWS = "numOfRows"
    const val QUERY_PAGE_NO = "pageNo"
    const val QUERY_TYPE = "type"
    const val QUERY_OPERATION_RULE_NUM = "operationRuleNm"

    // naverApi

    const val Query_QUERY = "query"
    const val QUERY_DISPLAY = "display"
    const val QUERY_START = "start"
    const val QUERY_SORT = "sort"



}