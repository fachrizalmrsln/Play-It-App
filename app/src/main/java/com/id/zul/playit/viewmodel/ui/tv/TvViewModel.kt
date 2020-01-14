package com.id.zul.playit.viewmodel.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.id.zul.playit.model.tv.Tv
import com.id.zul.playit.repository.CatalogRepository
import com.id.zul.playit.repository.source.dummy.DummyData

class TvViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    val dummyTv = DummyData.dataTv()

    fun getOnAir(page: Int): LiveData<List<Tv>> =
        catalogRepository.getOnAirTv(page)
}