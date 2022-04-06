package com.ubaya.ubayaeats_160419069.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.ubaya.ubayaeats_160419069.model.Restaurants

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    val restaurantsLiveData = MutableLiveData<Restaurants>()
    val TAG  = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(idResto:String){
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.56.1:8080/anmp/listrestaurant.php?id=${idResto}"

        val stringRequest = StringRequest(
            Request.Method.GET, url, {
                val results = Gson().fromJson<Restaurants>(it, Restaurants::class.java)
                restaurantsLiveData.value = results
                Log.d("showvolley", it)
            },{
                Log.d("errorvolley", it.toString())
            }
        ).apply {
            tag =TAG
        }
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}