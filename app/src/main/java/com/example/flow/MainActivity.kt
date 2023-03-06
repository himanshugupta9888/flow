package com.example.flow

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flow.adapter.ItemAdapter
import com.example.flow.data.Item
import com.example.flow.networksUtils.ItemRepository
import com.example.flow.viewmodel.ItemViewModel
import com.example.flow.viewmodel.ItemViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var itemViewModel: ItemViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()
        val itemViewModelFactory= ItemViewModelFactory(ItemRepository())
        itemViewModel= ViewModelProvider(this,itemViewModelFactory)[ItemViewModel::class.java]
        itemViewModel.getPost()
        itemViewModel.postData.observe(this, Observer {
            Log.d(TAG, "onCreate: ${it[0].body}")
            itemAdapter.setPostData(it as ArrayList<Item>)
            recyclerView.visibility=View.VISIBLE
        })

//        flow example

//        runBlocking{
//            getData().catch { e->
//                Log.e("Exception" ,e.message.toString())
//            }.collect{ it ->
//                Log.e("Flow value" ,it.toString())
//
//            }
//        }

//        flow of example

//        val data  = flowOf(1,2,3,4,5).flowOn(Dispatchers.IO)
//
//        runBlocking {
//           data.collect{
//               Log.e("Flow of" ,it.toString())
//           }
//        }

//        as flow example

//        val data  = listOf(1,2,3,4,5,6,7).asFlow().flowOn(Dispatchers.IO)
//        runBlocking {
//           data.collect{
//               Log.e("as Flow" ,it.toString())
//           }
//        }

//        map example

//        val data = flowOf(1, 2, 3, 4, 5, 6, 7, 8).flowOn(Dispatchers.IO)
//
//        runBlocking {
//            data.map { item ->
//                item*item
//            }.collect {
//                Log.e("map flow", it.toString())
//            }
//        }

//        filter flow

        val data = flowOf(1, 2, 3, 4, 5, 6, 7, 8).flowOn(Dispatchers.IO)

        runBlocking {
            data.filter { item ->
                item == 1
            }.collect {
                Log.e("filter flow", it.toString())
            }
        }
    }

    private fun getData() = flow<Int> {
        for (i in 1..5){
            kotlinx.coroutines.delay(500)
            emit(i)
        }

    }.flowOn(Dispatchers.IO)

    private fun initUi() {
        recyclerView=findViewById(R.id.recyclerView)
        itemAdapter= ItemAdapter(this, ArrayList())
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager= LinearLayoutManager(this@MainActivity)
            adapter=itemAdapter
        }
    }

}