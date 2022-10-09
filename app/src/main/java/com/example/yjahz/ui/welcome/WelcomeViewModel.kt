package com.example.yjahz.ui.welcome

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yjahz.model.Status
import com.example.yjahz.model.user.User
import com.example.yjahz.network.Keys
import com.example.yjahz.remote_source.AuthRemoteSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class WelcomeViewModel constructor(
    private var remoteSource: AuthRemoteSource,
    private var sharedPreferences: SharedPreferences
) : ViewModel() {


    var user = User()
    private val _clientStatus = MutableLiveData<Status>()
    val clientStatus: LiveData<Status> = _clientStatus


    fun getClient() {
        _clientStatus.postValue(Status.LOADING)

        //getting saved token if exists
        getToken()

        //fetching Client Profile from API
        fetchClientProfileFromApi()
    }

    private fun getToken() {
        sharedPreferences.getString("token", "null")?.apply {
            Keys.AuthorizationToken = "Bearer ${this.trim()}"
        }
    }

    private fun fetchClientProfileFromApi() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                user = remoteSource.getClientProfile()
                _clientStatus.postValue(Status.DONE)
            } catch (ex: Exception) {
                Log.e("TAG", "getClient: $ex ")
                _clientStatus.postValue(Status.ERROR)
            }
        }
    }


}