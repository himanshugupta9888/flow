package com.example.flow.networksUtils

import com.example.flow.data.Item
import retrofit2.http.GET


interface Api {
    @GET("posts")
    suspend fun getPost() : List<Item>
}