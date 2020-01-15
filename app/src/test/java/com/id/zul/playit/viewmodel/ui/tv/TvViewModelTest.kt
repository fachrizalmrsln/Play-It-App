package com.id.zul.playit.viewmodel.ui.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.id.zul.playit.model.tv.Tv
import com.id.zul.playit.repository.CatalogRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class TvViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: TvViewModel
    private lateinit var catalogRepository: CatalogRepository

    private lateinit var data: MutableLiveData<List<Tv>>
    private lateinit var dummy: List<Tv>

    private lateinit var observer: Observer<List<Tv>>

    @Before
    fun setUp() {
        catalogRepository = mock(CatalogRepository::class.java)
        viewModel = TvViewModel(catalogRepository)

        data = MutableLiveData()

        observer = mock(Observer::class.java) as Observer<List<Tv>>
    }

    @Test
    fun getTv() {
        dummy = viewModel.dummyTv

        data.postValue(dummy)

        `when`(catalogRepository.getOnAirTv(1)).thenReturn(data)

        viewModel.getOnAir(1).observeForever(observer)
        verify(observer).onChanged(dummy)

        assertEquals(dummy.size, 20)
    }
}