package com.example.codemobilechallengeapp.core.remote.model

sealed class Result<out R> {
    data class Success<out T>(val data: T?) : Result<T>()
    object Loading : Result<Nothing>()
    data class Error(val exception: Throwable) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            else -> "Error[exception=else]"
        }
    }
}