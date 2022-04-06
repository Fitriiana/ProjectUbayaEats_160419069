package com.ubaya.ubayaeats_160419069.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.ubayaeats_160419069.R
import com.ubaya.ubayaeats_160419069.util.loadImage
import com.ubaya.ubayaeats_160419069.viewModel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail_list.*
import kotlinx.android.synthetic.main.save_list_item.*


class DetailListFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        val idRestaurant = DetailListFragmentArgs.fromBundle(requireArguments()).restaurantID
        viewModel.fetch(idRestaurant)
        observeViewModel()

        buttonReviewDetail.setOnClickListener {
            val action = DetailListFragmentDirections.actionDetailListFragmentToReviewListFragment(idRestaurant)
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun observeViewModel() {
        viewModel.restaurantsLiveData.observe(viewLifecycleOwner){
            val resto =viewModel.restaurantsLiveData.value
            resto?.let {
                textRestaurantName.text = it.restaurantName
                textAddressResto.text = it.address
                textDetailHarga.text = it.price
                textDetailPhone.text = it.phone
                imageMenu.loadImage(it.menu, progressLoadMenu)
                textDistanceRest.text = it.distance
                textRateDetails.text = it.rate.toString()
                textTypeEat.text=it.restaurantType
                imageRest.loadImage(it.photoUrl, progressLoadDetail)
            }
        }
    }
}