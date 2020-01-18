package com.id.zul.playit.repository

import androidx.lifecycle.LiveData
import com.id.zul.playit.model.favorite.FavoriteEntity
import com.id.zul.playit.model.movie.Movie
import com.id.zul.playit.model.tv.Tv
import com.id.zul.playit.repository.source.local.LocalDataSource
import com.id.zul.playit.repository.source.local.LocalRepository
import com.id.zul.playit.repository.source.remote.RemoteDataSource
import com.id.zul.playit.repository.source.remote.RemoteRepository

class CatalogRepository(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository
) : RemoteDataSource, LocalDataSource {

    companion object {
        fun getInstance(
            remoteRepository: RemoteRepository,
            localRepository: LocalRepository
        ): CatalogRepository =
            CatalogRepository(remoteRepository, localRepository)
    }

    override fun getNowPlayingMovie(page: Int): LiveData<List<Movie>> =
        remoteRepository.getNowPlayingMovie(page)

    override fun getOnAirTv(page: Int): LiveData<List<Tv>> =
        remoteRepository.getOnAirTv(page)

    override fun getMovieById(id: Int): LiveData<Movie> =
        remoteRepository.getMovieById(id)

    override fun getTvById(id: Int): LiveData<Tv> =
        remoteRepository.getTvById(id)

    override fun insertFavorite(favoriteEntity: FavoriteEntity) =
        localRepository.insertFavorite(favoriteEntity)

    override fun deleteFavorite(favoriteEntity: FavoriteEntity) =
        localRepository.deleteFavorite(favoriteEntity)

    override fun getFavoriteMovie(): LiveData<List<FavoriteEntity>> =
        localRepository.getFavoriteMovie()

    override fun getFavoriteTv(): LiveData<List<FavoriteEntity>> =
        localRepository.getFavoriteTv()

    override fun getFavoriteById(id: Int): LiveData<FavoriteEntity> =
        localRepository.getFavoriteById(id)

}