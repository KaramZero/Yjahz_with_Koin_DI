package com.example.yjahz.ui.sign_up

import android.content.SharedPreferences
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yjahz.model.InputStatus
import com.example.yjahz.model.user.User
import com.example.yjahz.remote_source.AuthRemoteSource
import com.example.yjahz.model.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel constructor(
    private var remoteSource: AuthRemoteSource,
    private var sharedPreferences: SharedPreferences
) : ViewModel() {

    var user = User()
    private val _signUpStatus = MutableLiveData<Status?>()
    val signUpStatus: LiveData<Status?> = _signUpStatus

    private val _inputStatus = MutableLiveData<InputStatus?>()
    val inputStatus: LiveData<InputStatus?> = _inputStatus

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun signUp(
        name: String,
        email: String,
        password: String,
        confirmPassword: String,
        phone: String
    ) {
        //Check input validation then call Api
        if (inputIsValid(name, email, password, confirmPassword, phone))
            callApiToSignUp(name, email, password, phone)
    }

    private fun callApiToSignUp(
        name: String,
        email: String,
        password: String,
        phone: String
    ) {
        _signUpStatus.postValue(Status.LOADING)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                user = remoteSource.signUp(name, email, password, phone)
                sharedPreferences.edit().putString("token", user.token).apply()
                _signUpStatus.postValue(Status.DONE)
            } catch (ex: Exception) {
                Log.e("TAG", "logIn:  $ex")
                _signUpStatus.postValue(Status.ERROR)
                _message.postValue("${ex.message}")
            }
        }
    }

    private fun inputIsValid(
        name: String,
        email: String,
        password: String,
        confirmPassword: String,
        phone: String
    ): Boolean {
        var res = true
        if (name.isEmpty()) {
            _inputStatus.value = InputStatus.NAME
            res = false
        }
        if (!(email.contains('@') && Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
            _inputStatus.value = InputStatus.EMAIL
            res = false
        }
        if (phone.length < 11 || !Patterns.PHONE.matcher(phone).matches()) {
            _inputStatus.value = InputStatus.PHONE
            res = false
        }
        if (password.length < 6) {
            _inputStatus.value = InputStatus.PASSWORD
            res = false
        }
        if (confirmPassword != password) {
            _inputStatus.value = InputStatus.CONFIRM_PASSWORD
            res = false
        }
        return res
    }


}