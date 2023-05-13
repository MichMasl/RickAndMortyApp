package ru.mihmas.rickandmortyapp.feature_caching.location.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocationLocal(
    @PrimaryKey
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String
)