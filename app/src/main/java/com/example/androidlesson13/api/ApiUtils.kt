package com.example.androidlesson13.api

class ApiUtils {

    companion object{

        fun createApi():IApiManager{

            return RetrofitClient.createRetrofit().create(IApiManager::class.java)

        }
    }
}