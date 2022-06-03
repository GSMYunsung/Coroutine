package com.yunsung.coroutine.util

// Nothing 의 이해
//https://readystory.tistory.com/143

//<out R> 제네릭 타입의 이해
//https://medium.com/mj-studio/%EC%BD%94%ED%8B%80%EB%A6%B0-%EC%A0%9C%EB%84%A4%EB%A6%AD-in-out-3b809869610e

sealed class Result <T>(
    val data : T? = null,
    val message: String? = null
){
    class Success<T>(data : T) : Result<T>(data)

    class Error<T>(message: String?, data: T? = null): Result<T>(data, message)

    class Loading<T>: Result<T>()
}


