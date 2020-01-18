package com.id.zul.playit.repository

import androidx.lifecycle.LiveData
import com.id.zul.playit.model.movie.Movie
import com.id.zul.playit.model.tv.Tv
import com.id.zul.playit.repository.source.remote.RemoteDataSource
import com.id.zul.playit.repository.source.remote.RemoteRepository

class CatalogRepository(
    private val remoteRepository: RemoteRepository
) : RemoteDataSource {

    companion object {
        fun getInstance(remoteRepository: RemoteRepository): CatalogRepository =
            CatalogRepository(remoteRepository)
    }

    override fun getNowPlayingMovie(page: Int): LiveData<List<Movie>> =
        remoteRepository.getNowPlayingMovie(page)

    override fun getOnAirTv(page: Int): LiveData<List<Tv>> =
        remoteRepository.getOnAirTv(page)

    override fun getMovieById(id: Int): LiveData<Movie> =
        remoteRepository.getMovieById(id)

    override fun getTvById(id: Int): LiveData<Tv> =
        remoteRepository.getTvById(id)
}