package ru.mihmas.rickandmortyapp.feature_list.location.domain.usecase

import ru.mihmas.rickandmortyapp.feature_list.location.domain.repository.LocationRepository
import javax.inject.Inject

class RefreshListOfLocationsUseCase @Inject constructor(private val repository: LocationRepository) {
    fun refreshListOfLocations() {
        repository.refreshListOfLocations()
    }
}