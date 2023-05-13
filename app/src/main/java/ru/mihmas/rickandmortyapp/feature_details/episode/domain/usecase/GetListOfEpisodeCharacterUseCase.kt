package ru.mihmas.rickandmortyapp.feature_details.episode.domain.usecase

import ru.mihmas.rickandmortyapp.feature_details.episode.domain.model.EpisodeCharacterDetail
import ru.mihmas.rickandmortyapp.feature_details.episode.domain.repository.EpisodeDetailRepository
import javax.inject.Inject

class GetListOfEpisodeCharacterUseCase @Inject constructor(
    private val repository: EpisodeDetailRepository
) {
    suspend fun getListOfEpisodeCharacter(list: String) : List<EpisodeCharacterDetail> {
        return repository.getMultipleCharacters(list = list)
    }
}