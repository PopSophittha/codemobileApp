package com.example.codemobilechallengeapp.core.storage

import android.content.Context
import android.content.SharedPreferences

class PreferenceRepositoryImpl(private val context: Context) : PreferenceRepository {

    private fun get(): SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private fun getEditor(): SharedPreferences.Editor = get().edit()

    override fun saveAccessToken(accessToken: String?) {
        getEditor().putString(bundle_access_token, accessToken).apply()
    }

    override fun getAccessToken(): String? {
        return get().getString(bundle_access_token, "")
    }

    override fun clearAccessToken() {
        getEditor().remove(bundle_access_token).apply()
    }

    override fun clearAllData() {
        getEditor().clear().apply()
    }

    companion object {
        private const val PREFS_NAME = "tls_driver"
        private const val bundle_access_token: String = "access_token"
    }

}