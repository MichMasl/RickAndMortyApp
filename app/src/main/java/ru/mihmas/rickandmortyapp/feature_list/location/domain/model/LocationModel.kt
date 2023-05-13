package ru.mihmas.rickandmortyapp.feature_list.location.domain.model

data class LocationModel(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String
)