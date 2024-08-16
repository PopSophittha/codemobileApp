package com.example.codemobilechallengeapp.presentation.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.codemobilechallengeapp.core.remote.extension.isEmailValid
import com.example.codemobilechallengeapp.core.remote.extension.isNotEmptyStrings
import com.example.codemobilechallengeapp.core.remote.extension.isPasswordValid
import com.example.codemobilechallengeapp.core.remote.model.LoginData
import com.example.codemobilechallengeapp.core.remote.repository.LoginRepository
import com.example.codemobilechallengeapp.presentation.base.BaseViewModel
import com.example.codemobilechallengeapp.core.remote.model.Result
import com.example.codemobilechallengeapp.core.storage.PreferenceRepository
import com.example.codemobilechallengeapp.util.SharePreference
import kotlinx.coroutines.launch

class LoginViewModel(
    private val preferenceRepository: PreferenceRepository,
    private val loginRepository: LoginRepository
) : BaseViewModel() {

    val context: MutableLiveData<Context> = MutableLiveData()

    val email: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()

    private val _readyToLogin = MutableLiveData(false)
    val readyToLogin = _readyToLogin

    val navigateToMain = MutableLiveData<Boolean>()

    fun isReadyToLogin() {
        _readyToLogin.value = isNotEmptyStrings(email.value, password.value)
                && email.value.isEmailValid()
                && password.value.isPasswordValid()
    }

    fun requestLogin() {
        email.value?.let { email ->
            password.value?.let { password ->
                viewModelScope.launch {
                    _dataLoading.postValue(true)
                    when (val result = loginRepository.login(
                        loginData = LoginData(
                            email = email,
                            password = password
                        )
                    )) {
                        is Result.Success -> {
                            _dataLoading.postValue(false)
                            SharePreference(context.value!!).token = "has access token"
                            navigateToMain.postValue(true)
                        }
                        is Result.Error -> {
                            preferenceRepository.saveAccessToken(null)
                            _dataLoading.postValue(false)
                            _error.postValue(result.exception.message.orEmpty())
                        }
                        else -> {
                            //Nothing to do
                        }
                    }
                }
            }
        }
    }

}