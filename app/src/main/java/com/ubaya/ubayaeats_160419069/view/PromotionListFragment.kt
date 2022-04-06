package com.ubaya.ubayaeats_160419069.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.ubayaeats_160419069.R
import com.ubaya.ubayaeats_160419069.viewModel.PromoViewModel
import kotlinx.android.synthetic.main.fragment_promotion_list.*


class PromotionListFragment : Fragment() {
    private lateinit var viewModel: PromoViewModel
    private val promoListAdapter = PromotionListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_promotion_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(PromoViewModel::class.java)
        viewModel.refresh()

        recyclerViewPromo.layoutManager = LinearLayoutManager(context)
        recyclerViewPromo.adapter = promoListAdapter

        observeViewModel()

        refreshLayoutPromo.setOnRefreshListener {
            recyclerViewPromo.visibility = View.GONE
            textErrorPromo.visibility = View.GONE
            progressLoadPromo.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayoutPromo.isRefreshing = false
        }
    }

    fun observeViewModel() {
        viewModel.promoLiveData.observe(viewLifecycleOwner) {
            promoListAdapter.updatePromoList(it)
        }
        viewModel.promoLoadErrorLiveData.observe(viewLifecycleOwner){
            textErrorPromo.visibility = if(it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner){
            if (it){
                recyclerViewPromo.visibility = View.GONE
                progressLoadPromo.visibility = View.GONE
            }else{
                recyclerViewPromo.visibility = View.VISIBLE
                progressLoadPromo.visibility = View.GONE
            }
        }
    }
}