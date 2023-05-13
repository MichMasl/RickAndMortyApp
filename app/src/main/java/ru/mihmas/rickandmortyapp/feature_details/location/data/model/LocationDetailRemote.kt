package ru.mihmas.rickandmortyapp.feature_details.location.data.model

data class LocationDetailRemote(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String
)