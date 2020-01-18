package com.id.zul.playit.model.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val favoriteId: Int,
    val favoriteType: String,
    val favoriteTile: String,
    val favoriteRate: String,
    val favoriteDate: String,
    val favoriteAge: String,
    val favoriteDescription: String,
    val favoritePoster: String,
    val favoriteBackdrop: String
)