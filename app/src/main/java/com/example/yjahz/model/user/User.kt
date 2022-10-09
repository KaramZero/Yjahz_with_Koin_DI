package com.example.yjahz.model.user

import com.google.gson.annotations.SerializedName


data class User (

    @SerializedName("id"        ) var id        : Int?                 = null,
    @SerializedName("name"      ) var name      : String?              = null,
    @SerializedName("email"     ) var email     : String?              = null,
    @SerializedName("phone"     ) var phone     : String?              = null,
    @SerializedName("image"     ) var image     : String?              = null,
    @SerializedName("type"      ) var type      : Int?                 = null,
    @SerializedName("status"    ) var status    : Int?                 = null,
    @SerializedName("balance"   ) var balance   : String?              = null,
    @SerializedName("addresses" ) var addresses : ArrayList<Addresses> = arrayListOf(),
    @SerializedName("token"     ) var token     : String?              = null

)