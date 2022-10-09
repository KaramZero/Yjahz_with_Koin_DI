package com.example.yjahz.ui.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yjahz.databinding.TrendingItemBinding
import com.example.yjahz.model.seller.Seller

class TrendingSellerAdapter constructor(private val context: Context) :
    ListAdapter<Seller, TrendingSellerAdapter.ViewHolder>(SellerDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            TrendingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = getItem(position)
        holder.bind(seller = currentItem, context = context)
    }


    fun setData(sellerList: ArrayList<Seller>) {
        this.submitList(sellerList)
    }

    class ViewHolder(private val binding: TrendingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(seller: Seller, context: Context) {
            binding.apply {
                Glide.with(context)
                    .load(seller.image)
                    .into(productImageView)
            }
        }
    }

}

