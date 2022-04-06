package com.ubaya.ubayaeats_160419069.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ubaya.ubayaeats_160419069.R
import com.ubaya.ubayaeats_160419069.util.loadImage
import com.ubaya.ubayaeats_160419069.viewModel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.save_list_item.*


class ProfileFragment : Fragment() {
    private lateinit var viewModel: ProfileViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        viewModel.showData()

        observeViewModel()
    }
    fun observeViewModel() {
        viewModel.userLiveData.observe(viewLifecycleOwner) {
            val restaurantSave = viewModel.userLiveData.value
            restaurantSave?.let {
                textUsername.text = it.username
                textFollowers.text = it.followers.toString()
                textFollowing.text = it.following.toString()
                textReview.text = it.reviews.toString()
                imageProf.loadImage(it.imageProfile, progressLoadProfile)
            }
        }
    }
}