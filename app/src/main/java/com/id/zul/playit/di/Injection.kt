package com.id.zul.playit.di

import android.app.Application
import com.id.zul.playit.repository.CatalogRepository
import com.id.zul.playit.repository.source.local.LocalRepository
import com.id.zul.playit.repository.source.remote.RemoteRepository

object Injection {
    fun provideRepository(application: Application): CatalogRepository {
        val remoteRepository = RemoteRepository.getInstance()
        val localRepository = LocalRepository.getInstance(application)
        return CatalogRepository.getInstance(remoteRepository, localRepository)
    }
}