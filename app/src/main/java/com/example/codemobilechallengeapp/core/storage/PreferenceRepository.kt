package com.example.codemobilechallengeapp.core.storage

interface PreferenceRepository {

    fun saveAccessToken(accessToken: String?)
    fun getAccessToken(): String?
    fun clearAccessToken()

    fun clearAllData()

}