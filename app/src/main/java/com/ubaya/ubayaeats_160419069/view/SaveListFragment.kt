package com.ubaya.ubayaeats_160419069.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.ubayaeats_160419069.R
import com.ubaya.ubayaeats_160419069.viewModel.SaveViewModel
import kotlinx.android.synthetic.main.fragment_save_list.*


class SaveListFragment : Fragment() {
    private lateinit var viewModel: SaveViewModel
    private val saveListAdapter = SaveListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_save_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(SaveViewModel::class.java)
        viewModel.fetchSavedata()

        recViewSave.layoutManager = LinearLayoutManager(context)
        recViewSave.adapter = saveListAdapter

        observeViewModel()

        refreshLayoutSave.setOnRefreshListener {
            recViewSave.visibility = View.GONE
            progressLoadSave.visibility = View.VISIBLE
            textSaveError.visibility = View.GONE
            viewModel.fetchSavedata()
            refreshLayoutSave.isRefreshing = false
        }
    }
    fun observeViewModel() {
        viewModel.restaurantsLiveData.observe(viewLifecycleOwner) {
            saveListAdapter.updateSaveList(it)
        }
        viewModel.restaurantLoadErrorLiveData.observe(viewLifecycleOwner){
            textSaveError.visibility = if(it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner){
            if (it){
                recViewSave.visibility = View.GONE
                progressLoadSave.visibility = View.GONE
            }else{
                recViewSave.visibility = View.VISIBLE
                progressLoadSave.visibility = View.GONE
            }
        }
    }
}