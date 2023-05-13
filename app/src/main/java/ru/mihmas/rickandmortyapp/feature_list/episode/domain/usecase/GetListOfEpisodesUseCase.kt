package ru.mihmas.rickandmortyapp.feature_list.episode.domain.usecase

import ru.mihmas.rickandmortyapp.feature_list.episode.domain.model.EpisodeModel
import ru.mihmas.rickandmortyapp.feature_list.episode.domain.repository.EpisodeRepository
import javax.inject.Inject

class GetListOfEpisodesUseCase @Inject constructor(private val repository: EpisodeRepository) {
    suspend fun getListOfEpisodesUseCase(
        name: String,
        episode: String
    ) : List<EpisodeModel> {
        return repository.getListOfEpisodes(
            name = name,
            episode = episode
        )
    }
}