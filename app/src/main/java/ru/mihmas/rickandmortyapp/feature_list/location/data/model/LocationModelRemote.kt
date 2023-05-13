package ru.mihmas.rickandmortyapp.feature_list.location.data.model

data class LocationModelRemote(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String
)