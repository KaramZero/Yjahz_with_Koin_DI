package com.example.yjahz.model.category

import com.google.gson.annotations.SerializedName


data class CategoryResponse (

  @SerializedName("success"       ) var success      : Boolean?        = null,
  @SerializedName("response_code" ) var responseCode : Int?            = null,
  @SerializedName("message"       ) var message      : String?         = null,
  @SerializedName("data"          ) var categoryData : CategoryData?   = CategoryData()

)