package com.id.zul.playit.viewmodel.ui.tv

import com.id.zul.playit.repository.CatalogRepository
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito.mock

class TvViewModelTest {

    private lateinit var viewModel: TvViewModel
    private lateinit var catalogRepository: CatalogRepository

    @Before
    fun setUp() {
        catalogRepository = mock(CatalogRepository::class.java)
        viewModel = TvViewModel(catalogRepository)
    }

    @Test
    fun getDummyTv() {
        val data = viewModel.dummyTv

        assertNotNull(data)
        assertEquals(data.size, 20)
    }
}