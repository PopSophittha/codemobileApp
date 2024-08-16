package com.example.codemobilechallengeapp.core.remote.datasource

import com.example.codemobilechallengeapp.core.storage.PreferenceRepository
import com.example.codemobilechallengeapp.core.remote.model.Result

class GetMyAccessTokenDataSourceImpl(
    private val preferenceRepository: PreferenceRepository
) : GetMyAccessTokenDataSource {
    override fun getMyAccessToken() = Result.Success(
        preferenceRepository.getAccessToken()
    )
}