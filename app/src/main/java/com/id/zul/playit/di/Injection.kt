package com.id.zul.playit.di

import com.id.zul.playit.repository.CatalogRepository
import com.id.zul.playit.repository.source.remote.RemoteRepository

object Injection {
    fun provideRepository(): CatalogRepository {
        val remoteRepository = RemoteRepository.getInstance()
        return CatalogRepository.getInstance(remoteRepository)
    }
}