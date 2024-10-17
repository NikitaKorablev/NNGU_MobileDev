package com.app_lab5.task4

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app_lab5.databinding.ActivityMainTask4Binding
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainTask4Binding
    private lateinit var adapter: ListViewAdapter
    private lateinit var currencyApi: CbrApi

    private val list = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTask4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ListViewAdapter(list, this)
        binding.listView.adapter = adapter
        binding.mainMenuButton.setOnClickListener(this::onMainMenuButtonClicked)

        initApi()
        initList()
    }

    private fun initApi() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.cbr.ru/")
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
        currencyApi = retrofit.create(CbrApi::class.java)
    }

    private fun initList() {
        currencyApi.getCurrencyRates().enqueue(object : retrofit2.Callback<ValCurs> {
            override fun onResponse(call: Call<ValCurs>, response: retrofit2.Response<ValCurs>) {
                if (response.isSuccessful) {
                    Log.d("Response", response.body()?.toString().toString())
                    response.body()?.valutes?.forEach { valute ->
                        list.add(Item(valute.name?: "Undefined", valute.value?: "0"))
                    }
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<ValCurs>, t: Throwable) {
                list.add(Item("Exception", "0"))
                t.printStackTrace()
            }
        })
    }

    private fun onMainMenuButtonClicked(view: View?) {
        finish()
    }
}