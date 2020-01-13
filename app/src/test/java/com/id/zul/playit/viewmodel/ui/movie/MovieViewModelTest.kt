package com.id.zul.playit.viewmodel.ui.movie

import com.id.zul.playit.repository.CatalogRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel
    private lateinit var catalogRepository: CatalogRepository

    @Before
    fun setUp() {
        catalogRepository = mock(CatalogRepository::class.java)
        viewModel = MovieViewModel(catalogRepository)
    }

    @Test
    fun getDummyMovie() {
        val data = viewModel.dummyMovie

        assertNotNull(data)
        assertEquals(data.size, 20)
    }
}