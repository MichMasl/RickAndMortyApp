package ru.mihmas.rickandmortyapp.feature_caching.location.presentation.repository

import ru.mihmas.rickandmortyapp.feature_caching.location.presentation.model.CachedLocation

interface LocationCacheRepository {
    suspend fun getAllLocations() : List<CachedLocation>

    suspend fun findLocation(name: String) : List<CachedLocation>

    suspend fun filterLocations(type: String, dimension: String) : List<CachedLocation>
}