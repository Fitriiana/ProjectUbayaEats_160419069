package com.ubaya.ubayaeats_160419069.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.ubayaeats_160419069.R
import com.ubaya.ubayaeats_160419069.viewModel.NearMeViewModel
import kotlinx.android.synthetic.main.fragment_near_me_list.*


class NearMeListFragment : Fragment() {
    private lateinit var viewModel: NearMeViewModel
    private val restoListAdapter = NearMeListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_near_me_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(NearMeViewModel::class.java)
        viewModel.fetchNearMeData()

        recyclerViewNearMe.layoutManager = LinearLayoutManager(context)
        recyclerViewNearMe.adapter = restoListAdapter

        observeViewModel()

        refreshLayoutNear.setOnRefreshListener {
            recyclerViewNearMe.visibility = View.GONE
            textErrorNear.visibility = View.GONE
            progressLoadNear.visibility = View.VISIBLE
            viewModel.fetchNearMeData()
            refreshLayoutNear.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.restaurantsLiveData.observe(viewLifecycleOwner) {
            restoListAdapter.updateRestaurantsList(it)
        }
        viewModel.restaurantLoadErrorLiveData.observe(viewLifecycleOwner){
            textErrorNear.visibility = if(it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner){
            if (it){
                recyclerViewNearMe.visibility = View.GONE
                progressLoadNear.visibility = View.GONE
            }else{
                recyclerViewNearMe.visibility = View.VISIBLE
                progressLoadNear.visibility = View.GONE
            }
        }
    }
}