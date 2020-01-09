package com.id.zul.playit.network

import com.id.zul.playit.utilis.Network
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    fun getClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Network.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}