package ru.mihmas.rickandmortyapp.feature_caching.location.presentation.model

data class CachedLocation(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String
)