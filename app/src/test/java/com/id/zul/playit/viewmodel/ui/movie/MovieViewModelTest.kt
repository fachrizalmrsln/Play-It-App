package com.id.zul.playit.viewmodel.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.id.zul.playit.model.movie.Movie
import com.id.zul.playit.repository.CatalogueRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class MovieViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MovieViewModel
    private lateinit var catalogueRepository: CatalogueRepository

    private lateinit var data: MutableLiveData<List<Movie>>
    private lateinit var dummy: List<Movie>

    private lateinit var observer: Observer<List<Movie>>

    @Before
    fun setUp() {
        catalogueRepository = mock(CatalogueRepository::class.java)
        viewModel = MovieViewModel(catalogueRepository)

        data = MutableLiveData()

        observer = mock(Observer::class.java) as Observer<List<Movie>>
    }

    @Test
    fun getMovie() {
        dummy = viewModel.dummyMovie

        data.postValue(dummy)

        `when`(catalogueRepository.getNowPlayingMovie(1)).thenReturn(data)

        viewModel.getNowPlaying(1).observeForever(observer)
        verify(observer).onChanged(dummy)

        assertEquals(dummy.size, 20)
    }
}