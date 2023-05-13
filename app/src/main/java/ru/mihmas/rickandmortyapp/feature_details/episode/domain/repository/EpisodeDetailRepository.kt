package ru.mihmas.rickandmortyapp.feature_details.episode.domain.repository

import ru.mihmas.rickandmortyapp.feature_details.episode.domain.model.EpisodeCharacterDetail
import ru.mihmas.rickandmortyapp.feature_details.episode.domain.model.EpisodeDetail

interface EpisodeDetailRepository {
    suspend fun getEpisodeDetails(id: Int): EpisodeDetail
    suspend fun getMultipleCharacters(list: String) : List<EpisodeCharacterDetail>
}