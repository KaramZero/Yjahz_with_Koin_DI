package com.example.yjahz.model.category

import com.google.gson.annotations.SerializedName


data class Category (

  @SerializedName ("id"      ) var id    : Int?    = null,
  @SerializedName("name"    ) var name   : String? = null,
  @SerializedName("image"   ) var image  : String? = null,
  @SerializedName("active"  ) var active : Int?    = null,
  @SerializedName("name_ar" ) var nameAr : String? = null,
  @SerializedName("name_en" ) var nameEn : String? = null

)