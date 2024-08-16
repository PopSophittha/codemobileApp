package com.example.codemobilechallengeapp.core.remote.network.interceptor

import com.example.codemobilechallengeapp.core.remote.model.UnauthorizedEvent
import okhttp3.Interceptor
import okhttp3.Response
import org.greenrobot.eventbus.EventBus
import org.koin.core.component.KoinComponent

class UnauthorizedInterceptor : Interceptor, KoinComponent {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.code == 401) {
            EventBus.getDefault().post(UnauthorizedEvent())
        }
        return response
    }
}