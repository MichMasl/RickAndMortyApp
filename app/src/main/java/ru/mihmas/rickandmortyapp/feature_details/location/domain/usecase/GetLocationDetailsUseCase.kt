package ru.mihmas.rickandmortyapp.feature_details.location.domain.usecase

import io.reactivex.rxjava3.core.Single
import ru.mihmas.rickandmortyapp.feature_details.location.domain.model.LocationDetail
import ru.mihmas.rickandmortyapp.feature_details.location.domain.repository.LocationDetailRepository
import javax.inject.Inject

class GetLocationDetailsUseCase @Inject constructor(
    private val repository: LocationDetailRepository
) {
    fun getLocationDetails(id: Int) : Single<LocationDetail> {
        return repository.getLocationDetails(id = id)
    }
}