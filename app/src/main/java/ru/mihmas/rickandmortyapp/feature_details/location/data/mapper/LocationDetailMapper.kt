package ru.mihmas.rickandmortyapp.feature_details.location.data.mapper

import ru.mihmas.rickandmortyapp.feature_details.location.data.model.LocationCharacterDetailRemote
import ru.mihmas.rickandmortyapp.feature_details.location.data.model.LocationDetailRemote
import ru.mihmas.rickandmortyapp.feature_details.location.domain.model.LocationCharacterDetail
import ru.mihmas.rickandmortyapp.feature_details.location.domain.model.LocationDetail

class LocationDetailMapper {
    companion object {
        fun mapRemoteToDomain(model: LocationDetailRemote): LocationDetail = LocationDetail(
            id = model.id,
            name = model.name,
            type = model.type,
            dimension = model.dimension,
            residents = model.residents
        )

        fun characterListRemoteToDomain(
            list: List<LocationCharacterDetailRemote>
        ): List<LocationCharacterDetail> {
            val newList = mutableListOf<LocationCharacterDetail>()
            for (item in list) {
                newList.add(
                    LocationCharacterDetail(
                        id = item.id,
                        name = item.name,
                        species = item.species,
                        status = item.status,
                        gender = item.gender,
                        image = item.image
                    )
                )
            }
            return newList
        }
    }
}