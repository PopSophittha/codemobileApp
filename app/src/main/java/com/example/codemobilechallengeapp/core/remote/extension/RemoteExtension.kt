package com.example.codemobilechallengeapp.core.remote.extension

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

fun String.toRequestBodyWithType() = this.toRequestBody(this.toMediaTypeOrNull())