package ru.mihmas.rickandmortyapp.feature_list.episode.data.mapper

import ru.mihmas.rickandmortyapp.feature_caching.episode.data.model.EpisodeLocal
import ru.mihmas.rickandmortyapp.feature_list.episode.data.model.EpisodeModelRemote
import javax.inject.Inject

class EpisodeRemoteToLocalMapper @Inject constructor(private val remoteList: List<EpisodeModelRemote>) {
    fun map() : List<EpisodeLocal> {
        val list = mutableListOf<EpisodeLocal>()
        for (item in remoteList) {
            list.add(
                EpisodeLocal(
                    id = item.id,
                    name = item.name,
                    episode = item.episode,
                    airDate = item.air_date
                )
            )
        }
        return list
    }
}