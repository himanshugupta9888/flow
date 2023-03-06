package com.example.flow.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flow.data.Item
import com.example.flow.networksUtils.ItemRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class ItemViewModel(private val itemRepository: ItemRepository): ViewModel() {
    val postData: MutableLiveData<List<Item>> = MutableLiveData()

    /*  way 1 */
    fun getPost()
    {
        viewModelScope.launch {
            itemRepository.getPost()
                .catch { e->
                    Log.d("main", "getPost: ${e.message}")
                }
                .collect {postData1->
                    postData.value=postData1
                }
        }
    }
}