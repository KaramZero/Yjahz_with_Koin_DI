package com.example.yjahz.model.user

import com.google.gson.annotations.SerializedName


data class Addresses (

  @SerializedName("id"        ) var id        : Int?    = null,
  @SerializedName("lat"       ) var lat       : String? = null,
  @SerializedName("lng"       ) var lng       : String? = null,
  @SerializedName("address"   ) var address   : String? = null,
  @SerializedName("street"    ) var street    : String? = null,
  @SerializedName("building"  ) var building  : String? = null,
  @SerializedName("apartment" ) var apartment : String? = null,
  @SerializedName("floor"     ) var floor     : String? = null,
  @SerializedName("name"      ) var name      : String? = null

)