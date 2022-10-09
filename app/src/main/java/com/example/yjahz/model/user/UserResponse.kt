package com.example.yjahz.model.user

import com.google.gson.annotations.SerializedName


data class UserResponse (

    @SerializedName("success"       ) var success      : Boolean? = null,
    @SerializedName("response_code" ) var responseCode : Int?     = null,
    @SerializedName("message"       ) var message      : String?  = null,
    @SerializedName("data"          ) var user         : User?    = User()

)