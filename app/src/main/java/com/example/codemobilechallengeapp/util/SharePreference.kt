package com.example.codemobilechallengeapp.util

import android.content.Context
import android.content.SharedPreferences

class SharePreference(_context: Context) {

    private var pref: SharedPreferences
    private var editor: SharedPreferences.Editor

    // shared pref mode
    private var PRIVATE_MODE = 0

    var isFirstTimeLaunch: Boolean
        get() = pref.getBoolean(IS_FIRST_TIME_LAUNCH, true)
        set(isFirstTime) {
            editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime)
            editor.commit()
        }

    var token: String?
        get() = pref.getString(TOKEN, null)
        set(token) {
            editor.putString(TOKEN, token)
            editor.commit()
        }

    var lang: String?
        get() = pref.getString(LANG, "th")
        set(lang) {
            editor.putString(LANG, lang)
            editor.commit()
        }

    var accepted_pdpa: Boolean
        get() = pref.getBoolean(ACCEPTED_PDPA, false)
        set(accepted_pdpa) {
            editor.putBoolean(ACCEPTED_PDPA, accepted_pdpa)
            editor.commit()
        }

    init {
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    fun clearPref(){
        pref.edit().clear().apply()
    }

    fun putString(key: String, value: String){
        editor.putString(key, value).commit()
    }

    fun getString(key: String): String? = pref.getString(key, null)

    companion object {

        // Shared preferences file name
        private val PREF_NAME = "tls_pref"

        private val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"
        private val TOKEN = "token"
        private val ACCEPTED_PDPA = "accepted_pdpa"

        private val LANG = "pref_lang"
        private val HOME = "pref_home"
    }
}