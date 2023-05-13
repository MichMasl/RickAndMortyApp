package ru.mihmas.rickandmortyapp.feature_details.location.domain.usecase

import io.reactivex.rxjava3.core.Single
import ru.mihmas.rickandmortyapp.feature_details.location.domain.model.LocationCharacterDetail
import ru.mihmas.rickandmortyapp.feature_details.location.domain.repository.LocationDetailRepository
import javax.inject.Inject

class GetListOfLocationCharacterUseCase @Inject constructor(
    private val repository: LocationDetailRepository
){
    fun getListOfLocationCharacter(list: String) : Single<List<LocationCharacterDetail>> {
        return repository.getMultipleCharacters(list = list)
    }
}