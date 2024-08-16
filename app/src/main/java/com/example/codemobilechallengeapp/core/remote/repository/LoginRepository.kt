package com.example.codemobilechallengeapp.core.remote.repository

import com.example.codemobilechallengeapp.core.remote.model.LoginData
import com.example.codemobilechallengeapp.core.remote.model.Result

interface LoginRepository {

    suspend fun login(
        loginData: LoginData
    ): Result<Boolean>

}