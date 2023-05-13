package ru.mihmas.rickandmortyapp.feature_list.location.data.mapper

import ru.mihmas.rickandmortyapp.feature_list.location.data.model.LocationModelRemote
import ru.mihmas.rickandmortyapp.feature_list.location.domain.model.LocationModel
import javax.inject.Inject

class LocationRemoteToDomainMapper @Inject constructor(private val remoteList: List<LocationModelRemote>) {
    fun map() : List<LocationModel> {
        val list = mutableListOf<LocationModel>()
        for (item in remoteList) {
            list.add(
                LocationModel(
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