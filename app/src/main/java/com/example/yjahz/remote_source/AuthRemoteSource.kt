package com.example.yjahz.remote_source

import com.example.yjahz.model.user.User
import com.example.yjahz.network.Keys
import com.example.yjahz.network.YogahezAuthApiServices
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class AuthRemoteSource constructor(private var api: YogahezAuthApiServices) {


    suspend fun getClientProfile(): User {
        return api.getClientProfile(Keys.headers).user!!
    }

    suspend fun logIn(email: String, password: String): User {
        val res = api.logIn(getLogInRequestBody(email, password))

        if (res.success == false) throw Exception(res.message)

        return res.user!!
    }

    suspend fun signUp(
        name: String,
        email: String = "null",
        password: String = "null",
        phone: String
    ): User {
        val res = api.signUp(Keys.headers, getSignUpRequestBody(name, email, password, phone))

        if (res.success == false) throw Exception(res.message)

        return res.user!!
    }

    private fun getLogInRequestBody(email: String, password: String): RequestBody {

        val jsonReq = JSONObject()
        jsonReq.put("email", email)
        jsonReq.put("password", password)
        return jsonReq.toString().toRequestBody("application/json".toMediaTypeOrNull())
    }

    private fun getSignUpRequestBody(
        name: String,
        email: String = "null",
        password: String = "null",
        phone: String
    ): RequestBody {

        val jsonReq = JSONObject()
        jsonReq.put("name", name)
        jsonReq.put("email", email)
        jsonReq.put("password", password)
        jsonReq.put("phone", phone)
        return jsonReq.toString().toRequestBody("application/json".toMediaTypeOrNull())
    }

}