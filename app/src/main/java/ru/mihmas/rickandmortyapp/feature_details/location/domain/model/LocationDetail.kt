package ru.mihmas.rickandmortyapp.feature_details.location.domain.model

data class LocationDetail(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>
)