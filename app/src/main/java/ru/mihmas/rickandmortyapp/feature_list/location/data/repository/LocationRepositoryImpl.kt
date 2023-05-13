package ru.mihmas.rickandmortyapp.feature_list.location.data.repository

import ru.mihmas.rickandmortyapp.feature_list.location.data.datasource.LocationListProvider
import ru.mihmas.rickandmortyapp.feature_list.location.domain.model.LocationModel
import ru.mihmas.rickandmortyapp.feature_list.location.domain.repository.LocationRepository
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val locationListProvider: LocationListProvider
) : LocationRepository {
    override suspend fun getListOfLocations(
        name: String,
        type: String,
        dimension: String
    ): List<LocationModel> {
        return locationListProvider.getListOfLocations(
            name = name,
            type = type,
            dimension = dimension
        )
    }

    override fun refreshListOfLocations() {
        return locationListProvider.refreshListOfLocations()
    }
}