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
import com.ubaya.ubayaeats_160419069.viewModel.ReviewListViewModel
import kotlinx.android.synthetic.main.fragment_review_list.*


class ReviewListFragment : Fragment() {
    private lateinit var viewModel: ReviewListViewModel
    private val reviewListAdapter = ReviewListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ReviewListViewModel::class.java)
        val idRestaurant = ReviewListFragmentArgs.fromBundle(requireArguments()).idRestaurantReview
        viewModel.ShowDataReviews(idRestaurant)

        recyclerViewReviewList.layoutManager = LinearLayoutManager(context)
        recyclerViewReviewList.adapter = reviewListAdapter

        observeViewModel()

        buttonAddReview.setOnClickListener {
            val action = ReviewListFragmentDirections.actionReviewListFragmentToCreateReviewFragment()
            Navigation.findNavController(it).navigate(action)
        }

        refreshLayoutReviewList.setOnRefreshListener {
            recyclerViewReviewList.visibility = View.GONE
            textErrorReviewList.visibility = View.GONE
            progressLoadReviewList.visibility = View.VISIBLE
            viewModel.ShowDataReviews(idRestaurant)
            refreshLayoutReviewList.isRefreshing = false
        }
    }

     fun observeViewModel() {
         viewModel.reviewsLiveData.observe(viewLifecycleOwner) {
             reviewListAdapter.updateReviewsList(it)
         }
         viewModel.reviewLoadErrorLiveData.observe(viewLifecycleOwner){
             textErrorReviewList.visibility = if(it) View.VISIBLE else View.GONE
         }
         viewModel.loadingLiveData.observe(viewLifecycleOwner){
             if (it){
                 recyclerViewReviewList.visibility = View.GONE
                 progressLoadReviewList.visibility = View.GONE
             }else{
                 recyclerViewReviewList.visibility = View.VISIBLE
                 progressLoadReviewList.visibility = View.GONE
             }
         }
    }
}