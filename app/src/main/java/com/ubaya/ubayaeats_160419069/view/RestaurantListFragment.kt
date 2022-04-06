package com.ubaya.ubayaeats_160419069.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.ubayaeats_160419069.R
import com.ubaya.ubayaeats_160419069.viewModel.ListViewModel
import kotlinx.android.synthetic.main.fragment_restaurant_list.*
import kotlinx.android.synthetic.main.fragment_restaurant_list.view.*

class RestaurantListFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private val restoListAdapter = RestaurantListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = restoListAdapter

        observeViewModel()
        imageBtnNearme.setOnClickListener {
            val actionNear = RestaurantListFragmentDirections.actionItemHomeToNearMeListFragment()
            Navigation.findNavController(it).navigate(actionNear)
        }
        imageBtnPromo.setOnClickListener {
            val actionPromo = RestaurantListFragmentDirections.actionItemHomeToPromotionListFragment()
            Navigation.findNavController(it).navigate(actionPromo)
        }
        refreshLayout.setOnRefreshListener {
            recView.visibility = View.GONE
            textError.visibility = View.GONE
            progressLoad.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }
    }

     fun observeViewModel() {
        viewModel.restaurantsLiveData.observe(viewLifecycleOwner) {
            restoListAdapter.updateRestaurantsList(it)
        }
        viewModel.restaurantLoadErrorLiveData.observe(viewLifecycleOwner){
            textError.visibility = if(it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner){
            if (it){
                recView.visibility = View.GONE
                progressLoad.visibility = View.GONE
            }else{
                recView.visibility = View.VISIBLE
                progressLoad.visibility = View.GONE
            }
        }
    }
}