package com.id.zul.playit.viewmodel.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.id.zul.playit.model.movie.Movie
import com.id.zul.playit.model.tv.Tv
import com.id.zul.playit.repository.CatalogRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class DetailViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DetailViewModel
    private lateinit var catalogRepository: CatalogRepository

    private lateinit var dataMovie: MutableLiveData<Movie>
    private lateinit var dataTv: MutableLiveData<Tv>

    private lateinit var dummyMovieById: Movie
    private lateinit var dummyTvById: Tv

    private lateinit var observerMovie: Observer<Movie>
    private lateinit var observerTv: Observer<Tv>

    private var idMovieTest: Int = 0
    private var idTvTest: Int = 0

    private var idMovieDummy: Int = 0
    private var idTvDummy: Int = 0

    @Before
    fun setUp() {
        idMovieTest = 330457
        idMovieDummy = 578189

        idTvTest = 4057
        idTvDummy = 84661

        catalogRepository = mock(CatalogRepository::class.java)
        viewModel = DetailViewModel(catalogRepository)

        dataMovie = MutableLiveData()
        dataTv = MutableLiveData()

        observerMovie = mock(Observer::class.java) as Observer<Movie>
        observerTv = mock(Observer::class.java) as Observer<Tv>
    }

    @Test
    fun getMovieById() {
        dummyMovieById = viewModel.dummyMovieById(idMovieDummy)

        dataMovie.postValue(dummyMovieById)

        `when`(catalogRepository.getMovieById(idMovieTest)).thenReturn(dataMovie)

        viewModel.getMovieById(idMovieTest).observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyMovieById)
    }

    @Test
    fun getTvById() {
        dummyTvById = viewModel.dummyTvById(idTvDummy)

        dataTv.postValue(dummyTvById)

        `when`(catalogRepository.getTvById(idTvTest)).thenReturn(dataTv)

        viewModel.getTvById(idTvTest).observeForever(observerTv)
        verify(observerTv).onChanged(dummyTvById)
    }

}
