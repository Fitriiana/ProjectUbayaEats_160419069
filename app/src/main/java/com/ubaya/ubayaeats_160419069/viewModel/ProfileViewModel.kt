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
import com.ubaya.ubayaeats_160419069.model.User

class ProfileViewModel (application: Application) : AndroidViewModel(application){
    val userLiveData = MutableLiveData<User>()
    val TAG  = "volleyTag"
    private var queue: RequestQueue? = null

    fun showData(){
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.56.1:8080/anmp/profileUser.php?id=1"

        val stringRequest = StringRequest(
            Request.Method.GET, url, {
                val result = Gson().fromJson<User>(it, User::class.java)
                userLiveData.value = result
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