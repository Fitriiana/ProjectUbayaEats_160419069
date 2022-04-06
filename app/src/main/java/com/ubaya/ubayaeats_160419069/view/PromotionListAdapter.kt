package com.ubaya.ubayaeats_160419069.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayaeats_160419069.R
import com.ubaya.ubayaeats_160419069.model.Restaurants
import com.ubaya.ubayaeats_160419069.util.loadImage
import kotlinx.android.synthetic.main.promo_list_item.view.*

class PromotionListAdapter (var restaurantsList:ArrayList<Restaurants>) :
    RecyclerView.Adapter<RestaurantListAdapter.EatsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantListAdapter.EatsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.promo_list_item,parent,false)
        return RestaurantListAdapter.EatsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantListAdapter.EatsViewHolder, position: Int) {
        val restaurant = restaurantsList[position]
        with(holder.view){
            textDistRestaurant.text = restaurant.distance
            textTypeRestoPromo.text = restaurant.restaurantType
            imageRestaurant.loadImage(restaurant.photoUrl, progressLoadImagePromo)
            textRestName.text = restaurant.restaurantName
            textRateRestaurant.text = restaurant.rate.toString()
        }
    }
    fun updatePromoList(newPromotionsList:List<Restaurants>){
        restaurantsList.clear()
        restaurantsList.addAll(newPromotionsList)
        notifyDataSetChanged()
    }

    override fun getItemCount()= restaurantsList.size
}