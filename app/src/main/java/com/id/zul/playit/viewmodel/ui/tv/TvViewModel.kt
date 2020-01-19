package com.id.zul.playit.viewmodel.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.id.zul.playit.model.tv.Tv
import com.id.zul.playit.repository.CatalogueRepository
import com.id.zul.playit.repository.source.dummy.DummyData

class TvViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    val dummyTv = DummyData.dummyTv()

    fun getOnAir(page: Int): LiveData<List<Tv>> =
        catalogueRepository.getOnAirTv(page)
}