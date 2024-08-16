package com.example.codemobilechallengeapp.core.remote.extension

import android.os.Build
import android.text.Html
import androidx.core.text.HtmlCompat
import java.util.function.IntPredicate

fun isNotEmptyStrings(vararg strings: String?): Boolean {
    val list = strings.filter { s ->
        if (s == null) return false
        s.isNotBlank()
    }
    return list.size == strings.count()
}

fun String?.isEmailValid(): Boolean {
    return if (this.isNullOrEmpty()) {
        false
    } else {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+.[A-Za-z0-9.-]\$"
        this.matches(emailRegex.toRegex())
    }
}

fun String?.isPasswordValid(): Boolean {
    return if (this.isNullOrEmpty()) {
        false
    } else {
        this.length >= 8
    }
}