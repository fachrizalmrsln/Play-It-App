package com.id.zul.playit.viewmodel.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.id.zul.playit.model.tv.show.Tv
import com.id.zul.playit.repository.CatalogRepository
import com.id.zul.playit.repository.dummy.DummyData

class TvViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    val dataTv = DummyData.dataTv()

    fun getOnAir(page: Int): LiveData<List<Tv>> =
        catalogRepository.getOnAirTv(page)
}