package com.example.yjahz.model.seller

import com.google.gson.annotations.SerializedName


data class Information (

  @SerializedName("id"                   ) var id                  : Int?    = null,
  @SerializedName("identity_number"      ) var identityNumber      : String? = null,
  @SerializedName("tax_record"           ) var taxRecord           : String? = null,
  @SerializedName("activity"             ) var activity            : String? = null,
  @SerializedName("nationality"          ) var nationality         : String? = null,
  @SerializedName("vehicle_image"        ) var vehicleImage        : String? = null,
  @SerializedName("vehicle_tablet_image" ) var vehicleTabletImage  : String? = null,
  @SerializedName("vehicle_registration" ) var vehicleRegistration : String? = null,
  @SerializedName("driving_image"        ) var drivingImage        : String? = null

)