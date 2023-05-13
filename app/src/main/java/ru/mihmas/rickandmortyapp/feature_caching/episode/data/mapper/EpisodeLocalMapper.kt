package ru.mihmas.rickandmortyapp.feature_caching.episode.data.mapper

import ru.mihmas.rickandmortyapp.feature_caching.episode.data.model.EpisodeLocal
import ru.mihmas.rickandmortyapp.feature_caching.episode.presentation.model.CachedEpisode
import javax.inject.Inject

class EpisodeLocalMapper @Inject constructor(private val localList: List<EpisodeLocal>) {
    fun map(): List<CachedEpisode> {
        val list = mutableListOf<CachedEpisode>()
        for (item in localList) {
            list.add(
                CachedEpisode(
                    id = item.id,
                    name = item.name,
                    episode = item.episode,
                    airDate = item.airDate
                )
            )
        }
        return list
    }
}