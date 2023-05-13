package ru.mihmas.rickandmortyapp.feature_details.character.domain.usecase

import io.reactivex.rxjava3.core.Single
import ru.mihmas.rickandmortyapp.feature_details.character.domain.model.CharacterDetail
import ru.mihmas.rickandmortyapp.feature_details.character.domain.repository.CharacterDetailRepository
import javax.inject.Inject

class GetCharacterDetailsUseCase @Inject constructor(private val repository: CharacterDetailRepository) {
    fun getCharacterDetails(id: Int) : Single<CharacterDetail> {
        return repository.getCharacterDetails(id = id)
    }
}