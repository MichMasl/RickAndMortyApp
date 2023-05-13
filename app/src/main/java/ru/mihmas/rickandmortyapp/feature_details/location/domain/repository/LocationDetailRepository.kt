package ru.mihmas.rickandmortyapp.feature_details.location.domain.repository

import io.reactivex.rxjava3.core.Single
import ru.mihmas.rickandmortyapp.feature_details.location.domain.model.LocationCharacterDetail
import ru.mihmas.rickandmortyapp.feature_details.location.domain.model.LocationDetail

interface LocationDetailRepository {
    fun getLocationDetails(id: Int) : Single<LocationDetail>
    fun getMultipleCharacters(list: String) : Single<List<LocationCharacterDetail>>
}