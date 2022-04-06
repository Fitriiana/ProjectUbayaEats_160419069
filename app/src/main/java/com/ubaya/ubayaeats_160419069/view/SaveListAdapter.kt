package com.ubaya.ubayaeats_160419069.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayaeats_160419069.R
import com.ubaya.ubayaeats_160419069.model.Restaurants
import com.ubaya.ubayaeats_160419069.util.loadImage
import kotlinx.android.synthetic.main.save_list_item.view.*

class SaveListAdapter(var restaurantsList:ArrayList<Restaurants>) :
    RecyclerView.Adapter<RestaurantListAdapter.EatsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantListAdapter.EatsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.save_list_item,parent,false)
        return RestaurantListAdapter.EatsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantListAdapter.EatsViewHolder, position: Int) {
        val resto = restaurantsList[position]
        with(holder.view){
            imageSave.loadImage(resto.photoUrl,progressBarSave)
            textRestoName.text = resto.restaurantName
            textTipe.text = resto.restaurantType
            textDist.text = resto.distance
            textRate.text = resto.rate.toString()
        }
    }
    fun updateSaveList(newRestaurantsList:List<Restaurants>){
        restaurantsList.clear()
        restaurantsList.addAll(newRestaurantsList)
        notifyDataSetChanged()
    }
    override fun getItemCount() = restaurantsList.size
}