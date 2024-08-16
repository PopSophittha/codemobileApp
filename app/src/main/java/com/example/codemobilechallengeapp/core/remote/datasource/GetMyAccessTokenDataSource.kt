package com.example.codemobilechallengeapp.core.remote.datasource

import com.example.codemobilechallengeapp.core.remote.model.Result

interface GetMyAccessTokenDataSource {
    fun getMyAccessToken(): Result<String?>
}