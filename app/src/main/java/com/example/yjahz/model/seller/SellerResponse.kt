package com.example.yjahz.model.seller

import com.google.gson.annotations.SerializedName


data class SellerResponse (

  @SerializedName("success"       ) var success      : Boolean?          = null,
  @SerializedName("response_code" ) var responseCode : Int?              = null,
  @SerializedName("message"       ) var message      : String?           = null,
  @SerializedName("data"          ) var sellers      : ArrayList<Seller> = arrayListOf()

)