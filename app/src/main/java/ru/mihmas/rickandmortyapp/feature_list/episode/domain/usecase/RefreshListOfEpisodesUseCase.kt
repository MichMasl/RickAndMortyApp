package ru.mihmas.rickandmortyapp.feature_list.episode.domain.usecase

import ru.mihmas.rickandmortyapp.feature_list.episode.domain.repository.EpisodeRepository
import javax.inject.Inject

class RefreshListOfEpisodesUseCase @Inject constructor(private val repository: EpisodeRepository) {
    fun refreshListOfEpisodes() {
        return repository.refreshListOfEpisodes()
    }
}