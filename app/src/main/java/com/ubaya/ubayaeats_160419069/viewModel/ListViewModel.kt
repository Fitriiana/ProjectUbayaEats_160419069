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
import com.google.gson.reflect.TypeToken
import com.ubaya.ubayaeats_160419069.model.Restaurants
import org.json.JSONObject

class ListViewModel(application: Application) : AndroidViewModel(application) {
    val restaurantsLiveData = MutableLiveData<ArrayList<Restaurants>>()
    val restaurantLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val TAG  = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh() {
        restaurantLoadErrorLiveData.value = false
        loadingLiveData.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.56.1:8080/anmp/listrestaurant.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url, {
                val sType = object : TypeToken<ArrayList<Restaurants>>(){}.type
                val result = Gson().fromJson<ArrayList<Restaurants>>(it, sType)
                restaurantsLiveData.value = result
                loadingLiveData.value = false
                Log.d("showvolley", it)

            },{
                loadingLiveData.value = false
                restaurantLoadErrorLiveData.value = true
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