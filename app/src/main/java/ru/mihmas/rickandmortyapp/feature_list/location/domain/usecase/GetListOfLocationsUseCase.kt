package ru.mihmas.rickandmortyapp.feature_list.location.domain.usecase

import ru.mihmas.rickandmortyapp.feature_list.location.domain.model.LocationModel
import ru.mihmas.rickandmortyapp.feature_list.location.domain.repository.LocationRepository
import javax.inject.Inject

class GetListOfLocationsUseCase @Inject constructor(private val repository: LocationRepository) {
    suspend fun getListOfLocations(
        name: String,
        type: String,
        dimension: String
    ) : List<LocationModel> {
        return repository.getListOfLocations(
            name = name,
            type = type,
            dimension = dimension
        )
    }
}