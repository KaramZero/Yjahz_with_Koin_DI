package com.example.yjahz.model.category

import com.google.gson.annotations.SerializedName


data class CategoryData (

    @SerializedName("data"       ) var categories  : ArrayList<Category> = arrayListOf(),
    @SerializedName("cart_count" ) var cartCount   : Int?                = null

)
