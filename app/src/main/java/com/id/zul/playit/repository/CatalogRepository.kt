package com.id.zul.playit.repository

import androidx.lifecycle.LiveData
import com.id.zul.mtv.data.model.tv.Tv
import com.id.zul.playit.model.movie.Movie
import com.id.zul.playit.repository.source.RemoteRepository

class CatalogRepository(
    private val remoteRepository: RemoteRepository
) : CatalogDataSource {

    companion object {
        fun getInstance(
            remoteRepository: RemoteRepository
        ): CatalogRepository {
            return CatalogRepository(remoteRepository)
        }
    }

    override fun getNowPlayingMovie(page: Int): LiveData<List<Movie>> =
        remoteRepository.getNowPlayingMovie(page)

    override fun getOnAirTv(page: Int): LiveData<List<Tv>> =
        remoteRepository.getOnAirTv(page)

}