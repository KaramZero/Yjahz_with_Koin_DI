package com.example.yjahz.network

import android.util.Log

class Keys {


    companion object {

        const val LoginEndPoint = "login"
        const val ClientProfileEndPoint = "client-profile"
        const val SignUpEndPoint = "client-register"




        var AuthorizationToken ="null"
        set(value) {
            field = value
            headers = headerMap()
        }
        const val BASE_URL: String = "http://yogahez.fatoorah.sa/api/"
        private const val Content_Type = "application/json"

        var headers: Map<String, String> = headerMap()

        private fun headerMap(): HashMap<String, String> {
            val header: HashMap<String, String> = HashMap()
            header["Content-Type"] = Content_Type
            header["Accept"] = Content_Type
            header["Authorization"] = AuthorizationToken
            return header
        }

    }
}