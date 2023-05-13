package ru.mihmas.rickandmortyapp.feature_details.character.data.mapper

import ru.mihmas.rickandmortyapp.feature_details.character.data.model.CharacterDetailRemote
import ru.mihmas.rickandmortyapp.feature_details.character.data.model.CharacterEpisodeDetailRemote
import ru.mihmas.rickandmortyapp.feature_details.character.domain.model.CharacterDetail
import ru.mihmas.rickandmortyapp.feature_details.character.domain.model.CharacterEpisodeDetail

class CharacterDetailMapper {
    companion object {
        fun mapRemoteToDomain(model: CharacterDetailRemote): CharacterDetail = CharacterDetail(
            id = model.id,
            name = model.name,
            status = model.status,
            species = model.species,
            type = model.type,
            gender = model.gender,
            image = model.image,
            episode = model.episode,
            originName = model.origin.name,
            originUrl = model.origin.url,
            locationName = model.location.name,
            locationUrl = model.location.url
        )

        fun episodeListRemoteToDomain(list: List<CharacterEpisodeDetailRemote>): List<CharacterEpisodeDetail> {
            val newList = mutableListOf<CharacterEpisodeDetail>()
            for (item in list) {
                newList.add(
                    CharacterEpisodeDetail(
                        id = item.id,
                        name = item.name,
                        episode = item.episode,
                        airDate = item.air_date
                    )
                )
            }
            return newList
        }
    }
}
