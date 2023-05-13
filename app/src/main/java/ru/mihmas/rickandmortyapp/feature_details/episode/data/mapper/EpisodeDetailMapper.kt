package ru.mihmas.rickandmortyapp.feature_details.episode.data.mapper

import ru.mihmas.rickandmortyapp.feature_details.episode.data.model.EpisodeCharacterDetailRemote
import ru.mihmas.rickandmortyapp.feature_details.episode.data.model.EpisodeDetailRemote
import ru.mihmas.rickandmortyapp.feature_details.episode.domain.model.EpisodeCharacterDetail
import ru.mihmas.rickandmortyapp.feature_details.episode.domain.model.EpisodeDetail

class EpisodeDetailMapper {
    companion object {
        fun mapRemoteToDomain(model: EpisodeDetailRemote) : EpisodeDetail = EpisodeDetail(
            id = model.id,
            name = model.name,
            episode = model.episode,
            airDate = model.air_date,
            characters = model.characters
        )

        fun characterListRemoteToDomain(list: List<EpisodeCharacterDetailRemote>) : List<EpisodeCharacterDetail> {
            val newList = mutableListOf<EpisodeCharacterDetail>()
            for (item in list) {
                newList.add(
                    EpisodeCharacterDetail(
                        id = item.id,
                        name = item.name,
                        status = item.status,
                        species = item.species,
                        gender = item.gender,
                        image = item.image
                    )
                )
            }
            return newList
        }
    }
}