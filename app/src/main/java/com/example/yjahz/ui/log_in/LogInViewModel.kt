package com.example.yjahz.ui.log_in

import android.content.SharedPreferences
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yjahz.model.InputStatus
import com.example.yjahz.model.Status
import com.example.yjahz.model.user.User
import com.example.yjahz.remote_source.AuthRemoteSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LogInViewModel constructor(
    private var remoteSource: AuthRemoteSource, private var sharedPreferences: SharedPreferences
) : ViewModel() {

    var user = User()
    private val _logInStatus = MutableLiveData<Status?>()
    val logInStatus: LiveData<Status?> = _logInStatus

    private val _inputStatus = MutableLiveData<InputStatus?>()
    val inputStatus: LiveData<InputStatus?> = _inputStatus


    fun logIn(email: String = "null", password: String = "null") {

        if (inputIsValid(email, password)) {
            _logInStatus.postValue(Status.LOADING)
            callApiToLogIn(email, password)
        }
    }

    private fun callApiToLogIn(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                user = remoteSource.logIn(email, password)
                sharedPreferences.edit().putString("token", user.token).apply()
                _logInStatus.postValue(Status.DONE)
            } catch (ex: Exception) {
                Log.e("TAG", "logIn:  $ex")
                when (ex.message) {
                    "wrong password" -> _inputStatus.postValue(InputStatus.PASSWORD)
                    "User is not existed!" -> _inputStatus.postValue(InputStatus.EMAIL)
                }
                _logInStatus.postValue(Status.ERROR)
            }
        }
    }


    private fun inputIsValid(
        email: String,
        password: String
    ): Boolean {
        var res = true

        if (!(email.contains('@') && Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
            _inputStatus.value = InputStatus.EMAIL
            res = false
        }

        if (password.length < 6) {
            _inputStatus.value = InputStatus.PASSWORD
            res = false
        }

        return res
    }


}