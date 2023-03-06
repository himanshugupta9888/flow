package com.example.flow.networksUtils

import com.example.flow.data.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class ItemRepository {
    fun getPost() : Flow<List<Item>> = flow {
        val postList= RetrofitBuilder.api.getPost()
        emit(postList)
    }.flowOn(Dispatchers.IO)
}