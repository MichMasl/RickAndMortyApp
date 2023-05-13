package ru.mihmas.rickandmortyapp.feature_details.character.domain.usecase

import io.reactivex.rxjava3.core.Single
import ru.mihmas.rickandmortyapp.feature_details.character.domain.model.CharacterEpisodeDetail
import ru.mihmas.rickandmortyapp.feature_details.character.domain.repository.CharacterDetailRepository
import javax.inject.Inject

class GetListOfCharacterEpisodeUseCase @Inject constructor(
    private val repository: CharacterDetailRepository
) {
    fun getListOfCharactersEpisodes(list: String) : Single<List<CharacterEpisodeDetail>> {
        return repository.getMultipleEpisodes(list = list)
    }
}