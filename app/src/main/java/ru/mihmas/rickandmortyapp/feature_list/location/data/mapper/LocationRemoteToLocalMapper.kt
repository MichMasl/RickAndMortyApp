package ru.mihmas.rickandmortyapp.feature_list.location.data.mapper

import ru.mihmas.rickandmortyapp.feature_caching.location.data.model.LocationLocal
import ru.mihmas.rickandmortyapp.feature_list.location.data.model.LocationModelRemote
import javax.inject.Inject

class LocationRemoteToLocalMapper @Inject constructor(private val remoteList: List<LocationModelRemote>) {
    fun map() : List<LocationLocal> {
        val list = mutableListOf<LocationLocal>()
        for (item in remoteList) {
            list.add(
                LocationLocal(
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