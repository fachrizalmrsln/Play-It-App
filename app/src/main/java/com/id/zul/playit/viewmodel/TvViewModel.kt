package com.id.zul.playit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.id.zul.mtv.data.model.tv.Tv
import com.id.zul.playit.repository.CatalogRepository

class TvViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    fun getOnAir(page: Int): LiveData<List<Tv>> {
        return catalogRepository.getOnAirTv(page)
    }
}