package ru.mihmas.rickandmortyapp.feature_list.location.data.model

data class LocationResponseInfo(
    val count: Int,
    val next: String?,
    val pages: Int,
    val prev: String?
)