package ru.mihmas.rickandmortyapp.feature_list.location.domain.repository

import ru.mihmas.rickandmortyapp.feature_list.location.domain.model.LocationModel

interface LocationRepository {
    suspend fun getListOfLocations(
        name: String,
        type: String,
        dimension: String
    ): List<LocationModel>

    fun refreshListOfLocations()
}