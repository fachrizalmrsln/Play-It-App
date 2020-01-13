package com.id.zul.playit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.id.zul.playit.model.tv.show.Tv
import com.id.zul.playit.repository.CatalogRepository

class TvViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    fun getOnAir(page: Int): LiveData<List<Tv>> =
        catalogRepository.getOnAirTv(page)
}