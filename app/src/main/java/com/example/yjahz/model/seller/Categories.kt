package com.example.yjahz.model.seller

import com.google.gson.annotations.SerializedName


data class Categories (

  @SerializedName("id"     ) var id     : Int?    = null,
  @SerializedName("name"   ) var name   : String? = null,
  @SerializedName("image"  ) var image  : String? = null,
  @SerializedName("active" ) var active : Int?    = null

)