package com.example.yjahz.model.seller

import com.google.gson.annotations.SerializedName


data class Seller (

    @SerializedName("id"                 ) var id                : Int?                         = null,
    @SerializedName("name"               ) var name              : String?                      = null,
    @SerializedName("email"              ) var email             : String?                      = null,
    @SerializedName("phone"              ) var phone             : String?                      = null,
    @SerializedName("image"              ) var image             : String?                      = null,
    @SerializedName("logo"               ) var logo              : String?                      = null,
    @SerializedName("description"        ) var description       : String?                      = null,
    @SerializedName("distance"           ) var distance          : String?                      = null,
    @SerializedName("type"               ) var type              : Int?                         = null,
    @SerializedName("status"             ) var status            : Int?                         = null,
    @SerializedName("lat"                ) var lat               : String?                      = null,
    @SerializedName("lng"                ) var lng               : String?                      = null,
    @SerializedName("address"            ) var address           : String?                      = null,
    @SerializedName("appointments"       ) var appointments      : String?                      = null,
    @SerializedName("trending"           ) var trending          : Int?                         = null,
    @SerializedName("popular"            ) var popular           : Int?                         = null,
    @SerializedName("rate"               ) var rate              : String?                      = null,
    @SerializedName("is_favorite"        ) var isFavorite        : Boolean?                     = null,
    @SerializedName("categories"         ) var categories        : ArrayList<Categories>        = arrayListOf(),
    @SerializedName("token"              ) var token             : String?                      = null,
    @SerializedName("information"        ) var information       : Information?                 = Information(),
    @SerializedName("product_categories" ) var productCategories : ArrayList<ProductCategories> = arrayListOf()

)