package com.ubaya.ubayaeats_160419069.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayaeats_160419069.R
import com.ubaya.ubayaeats_160419069.model.Restaurants
import com.ubaya.ubayaeats_160419069.util.loadImage
import kotlinx.android.synthetic.main.restaurant_list_item.view.*


class NearMeListAdapter (var restaurantsList:ArrayList<Restaurants>) :
    RecyclerView.Adapter<RestaurantListAdapter.EatsViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantListAdapter.EatsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.restaurant_list_item,parent,false)
        return RestaurantListAdapter.EatsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantListAdapter.EatsViewHolder, position: Int) {
        val resto = restaurantsList[position]
        with(holder.view) {
            textNameResto.text = resto.restaurantName
            textNeighborhood.text = resto.neighborhood
            textTypeResto.text = resto.restaurantType
            imageResto.loadImage(resto.photoUrl, progressLoadRestaurant)
            textJarakResto.text = resto.distance

            buttonDetail.setOnClickListener {
                val action = NearMeListFragmentDirections.actionNearMeListFragmentToDetailListFragment(resto.id.toString())
                Navigation.findNavController(it).navigate(action)
            }
        }
    }
    fun updateRestaurantsList(newRestaurantsList:List<Restaurants>){
        restaurantsList.clear()
        restaurantsList.addAll(newRestaurantsList)
        notifyDataSetChanged()
    }
    override fun getItemCount() = restaurantsList.size

}