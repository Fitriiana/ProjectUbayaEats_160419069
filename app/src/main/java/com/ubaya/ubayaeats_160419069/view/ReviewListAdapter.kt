package com.ubaya.ubayaeats_160419069.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayaeats_160419069.R
import com.ubaya.ubayaeats_160419069.model.Reviews
import com.ubaya.ubayaeats_160419069.util.loadImage
import kotlinx.android.synthetic.main.review_list_item.view.*

class ReviewListAdapter (var reviewsList:ArrayList<Reviews>) :
    RecyclerView.Adapter<RestaurantListAdapter.EatsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantListAdapter.EatsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.review_list_item,parent,false)
        return RestaurantListAdapter.EatsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantListAdapter.EatsViewHolder, position: Int) {
        val review = reviewsList[position]
        with(holder.view){
            textRateReview.text= review.rating.toString()
            textComments.text = review.comments
            textUsernameReviewer.text = review.name
            textTglReview.text = review.date
            imageProfReviewItem.loadImage(review.profile, progressLoadProfReviewListItem)
        }
    }
    fun updateReviewsList(newReviewsList:List<Reviews>){
        reviewsList.clear()
        reviewsList.addAll(newReviewsList)
        notifyDataSetChanged()
    }
    override fun getItemCount() = reviewsList.size
}