package com.example.yjahz.ui.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yjahz.databinding.PopularItemBinding
import com.example.yjahz.model.seller.Seller


class PopularSellerAdapter constructor(private val context: Context) :
    ListAdapter<Seller, PopularSellerAdapter.ViewHolder>(SellerDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PopularItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = getItem(position)

        holder.bind(seller = currentItem, context = context)

    }

    fun setData(sellerList: ArrayList<Seller>) {
        this.submitList(sellerList)
    }

    class ViewHolder(private val binding: PopularItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(seller: Seller, context: Context) {
            binding.apply {
                titleTxt.text = seller.name
                ratingBar.setRating(seller.rate?.toFloat() ?: 5.0f)
                ratingTextView.text = seller.rate
                val dis = "${seller.distance?.toInt()?.div(100)?.div(10.0)} Km"
                distance.text = dis
                Glide.with(context)
                    .load(seller.image)
                    .into(productImageView)
            }
        }
    }

}


class SellerDiffCallback : DiffUtil.ItemCallback<Seller>() {
    override fun areItemsTheSame(oldItem: Seller, newItem: Seller): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Seller, newItem: Seller): Boolean {
        return oldItem == newItem
    }

}