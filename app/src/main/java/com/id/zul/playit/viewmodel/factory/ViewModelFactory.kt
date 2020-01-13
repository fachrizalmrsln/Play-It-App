package com.id.zul.playit.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.id.zul.playit.di.Injection
import com.id.zul.playit.repository.CatalogRepository
import com.id.zul.playit.viewmodel.ui.detail.DetailViewModel
import com.id.zul.playit.viewmodel.ui.movie.MovieViewModel
import com.id.zul.playit.viewmodel.ui.tv.TvViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(private val catalogRepository: CatalogRepository) :
    ViewModelProvider.Factory {

    companion object {
        private var INSTANCE: ViewModelFactory? = null
        fun getInstance(): ViewModelFactory? {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE =
                            ViewModelFactory(
                                Injection.provideRepository()
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
                MovieViewModel(
                    catalogRepository
                ) as (T)
            modelClass.isAssignableFrom(TvViewModel::class.java) ->
                TvViewModel(catalogRepository) as (T)
            modelClass.isAssignableFrom(DetailViewModel::class.java) ->
                DetailViewModel(
                    catalogRepository
                ) as (T)
            else -> throw IllegalArgumentException("Error view model from " + modelClass.name)
        }
    }

}