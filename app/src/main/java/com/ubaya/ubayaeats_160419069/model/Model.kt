package com.ubaya.ubayaeats_160419069.model

import com.google.gson.annotations.SerializedName

data class Restaurants(
    var id:String?,
    @SerializedName("name")
    var restaurantName: String?,
    @SerializedName("location")
    var neighborhood:String?,
    @SerializedName("image")
    var photoUrl:String?,
    var address:String?,
    @SerializedName("cuisine_type")
    var restaurantType:String?,
    var distance:String?,
    var price:String?,
    var phone:String?,
    var rate:Float?,
    var menu:String?
)
data class User(
    var username:String?,
    var reviews:Int?,
    var followers:Int?,
    var following:Int?,
    var imageProfile:String?
)
data class Reviews(
    var name:String?,
    var date:String?,
    var rating:Int?,
    var comments:String?,
    var profile:String?
)