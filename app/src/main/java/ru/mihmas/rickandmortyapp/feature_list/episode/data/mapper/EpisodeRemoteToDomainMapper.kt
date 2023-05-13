package ru.mihmas.rickandmortyapp.feature_list.episode.data.mapper

import ru.mihmas.rickandmortyapp.feature_list.episode.data.model.EpisodeModelRemote
import ru.mihmas.rickandmortyapp.feature_list.episode.domain.model.EpisodeModel
import javax.inject.Inject

class EpisodeRemoteToDomainMapper @Inject constructor(private val remoteList: List<EpisodeModelRemote>) {
    fun map() : List<EpisodeModel> {
        val list = mutableListOf<EpisodeModel>()
        for (item in remoteList) {
            list.add(
                EpisodeModel(
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