package ru.mihmas.rickandmortyapp.feature_list.location.data.model

data class LocationRemoteResponse(
    val info: LocationResponseInfo,
    val results: List<LocationModelRemote>
)