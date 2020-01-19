package com.id.zul.playit.viewmodel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.id.zul.playit.di.Injection
import com.id.zul.playit.repository.CatalogueRepository
import com.id.zul.playit.viewmodel.ui.detail.DetailViewModel
import com.id.zul.playit.viewmodel.ui.favorite.FavoriteViewModel
import com.id.zul.playit.viewmodel.ui.movie.MovieViewModel
import com.id.zul.playit.viewmodel.ui.tv.TvViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(private val catalogueRepository: CatalogueRepository) :
    ViewModelProvider.Factory {

    companion object {
        private var INSTANCE: ViewModelFactory? = null
        fun getInstance(application: Application): ViewModelFactory? {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE =
                            ViewModelFactory(
                                Injection.provideRepository(application)
                            )
                    }
                }
            }
            return INSTANCE
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) ->
                MovieViewModel(catalogueRepository) as (T)
            modelClass.isAssignableFrom(TvViewModel::class.java) ->
                TvViewModel(catalogueRepository) as (T)
            modelClass.isAssignableFrom(DetailViewModel::class.java) ->
                DetailViewModel(catalogueRepository) as (T)
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) ->
                FavoriteViewModel(catalogueRepository) as (T)
            else -> throw IllegalArgumentException("Error view model from " + modelClass.name)
        }
    }

}