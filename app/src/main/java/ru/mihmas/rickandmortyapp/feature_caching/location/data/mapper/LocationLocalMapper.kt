package ru.mihmas.rickandmortyapp.feature_caching.location.data.mapper

import ru.mihmas.rickandmortyapp.feature_caching.location.data.model.LocationLocal
import ru.mihmas.rickandmortyapp.feature_caching.location.presentation.model.CachedLocation
import javax.inject.Inject

class LocationLocalMapper @Inject constructor(private val localList: List<LocationLocal>) {
    fun map(): List<CachedLocation> {
        val list = mutableListOf<CachedLocation>()
        for (item in localList) {
            list.add(
                CachedLocation(
                    id = item.id,
                    name = item.name,
                    type = item.type,
                    dimension = item.dimension
                )
            )
        }
        return list
    }
}