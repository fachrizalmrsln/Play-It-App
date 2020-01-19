package com.id.zul.playit.viewmodel.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.id.zul.playit.model.favorite.FavoriteEntity
import com.id.zul.playit.repository.CatalogRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class FavoriteViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var catalogRepository: CatalogRepository

    private lateinit var data: MutableLiveData<PagedList<FavoriteEntity>>

    private lateinit var observer: Observer<PagedList<FavoriteEntity>>
    private lateinit var pagedData: PagedList<FavoriteEntity>

    @Before
    fun setUp() {
        catalogRepository = mock(CatalogRepository::class.java)
        viewModel = FavoriteViewModel(catalogRepository)

        data = MutableLiveData()

        observer = mock(Observer::class.java) as Observer<PagedList<FavoriteEntity>>
        pagedData = mock(PagedList::class.java) as PagedList<FavoriteEntity>
    }

    @Test
    fun getFavoriteMovie() {
        data.postValue(pagedData)

        `when`(catalogRepository.getFavoriteMovie()).thenReturn(data)

        viewModel.getFavoriteMovie().observeForever(observer)
        verify(observer).onChanged(pagedData)

        assertEquals(pagedData.size, 0)
    }

    @Test
    fun getFavoriteTv() {
        data.postValue(pagedData)

        `when`(catalogRepository.getFavoriteTv()).thenReturn(data)

        viewModel.getFavoriteTv().observeForever(observer)
        verify(observer).onChanged(pagedData)

        assertEquals(pagedData.size, 0)
    }

}