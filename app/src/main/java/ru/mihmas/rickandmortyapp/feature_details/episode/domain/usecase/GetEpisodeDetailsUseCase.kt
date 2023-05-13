package ru.mihmas.rickandmortyapp.feature_details.episode.domain.usecase

import ru.mihmas.rickandmortyapp.feature_details.episode.domain.model.EpisodeDetail
import ru.mihmas.rickandmortyapp.feature_details.episode.domain.repository.EpisodeDetailRepository
import javax.inject.Inject

class GetEpisodeDetailsUseCase @Inject constructor(
    private val repository: EpisodeDetailRepository
) {
    suspend fun getEpisodeDetails(id: Int) : EpisodeDetail {
        return repository.getEpisodeDetails(id = id)
    }
}