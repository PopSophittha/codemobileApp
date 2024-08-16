package com.example.codemobilechallengeapp.core.remote.repository

import com.example.codemobilechallengeapp.core.remote.model.LoginData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.codemobilechallengeapp.core.remote.model.Result
import com.example.codemobilechallengeapp.core.storage.PreferenceRepository

class LoginRepositoryImpl(
    private val preferenceRepository: PreferenceRepository
) : LoginRepository {

    private val defaultEmail = "aa@bb.cc"
    private val defaultPassword = "12345678"

    override suspend fun login(loginData: LoginData) = withContext(Dispatchers.IO) {
        try {
            if (defaultEmail == loginData.email && defaultPassword == loginData.password) {
//                preferenceRepository.saveAccessToken(loginData.email)
                return@withContext Result.Success(true)
            } else
                return@withContext Result.Error(Exception("Email or password incorrect"))
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }
}