package ru.mihmas.rickandmortyapp.feature_caching.location.data.repository

import ru.mihmas.rickandmortyapp.feature_caching.location.data.local.LocationDao
import ru.mihmas.rickandmortyapp.feature_caching.location.data.mapper.LocationLocalMapper
import ru.mihmas.rickandmortyapp.feature_caching.location.presentation.model.CachedLocation
import ru.mihmas.rickandmortyapp.feature_caching.location.presentation.repository.LocationCacheRepository
import javax.inject.Inject

class LocationCacheRepositoryImpl @Inject constructor(
    private val dao: LocationDao
) : LocationCacheRepository {
    override suspend fun getAllLocations(): List<CachedLocation> {
        val localList = dao.getAllLocations()
        return LocationLocalMapper(localList).map()
    }

    override suspend fun findLocation(name: String): List<CachedLocation> {
        val localList = dao.findLocation(name)
        return LocationLocalMapper(localList).map()
    }

    override suspend fun filterLocations(type: String, dimension: String): List<CachedLocation> {
        val localList = dao.filterLocations(type = type, dimension = dimension)
        return LocationLocalMapper(localList).map()
    }
}