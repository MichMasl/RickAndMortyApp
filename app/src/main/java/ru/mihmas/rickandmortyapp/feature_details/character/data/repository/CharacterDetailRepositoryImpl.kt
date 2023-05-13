package ru.mihmas.rickandmortyapp.feature_details.character.data.repository

import io.reactivex.rxjava3.core.Single
import ru.mihmas.rickandmortyapp.feature_details.character.data.mapper.CharacterDetailMapper
import ru.mihmas.rickandmortyapp.feature_details.character.data.network.CharacterDetailApi
import ru.mihmas.rickandmortyapp.feature_details.character.domain.model.CharacterDetail
import ru.mihmas.rickandmortyapp.feature_details.character.domain.model.CharacterEpisodeDetail
import ru.mihmas.rickandmortyapp.feature_details.character.domain.repository.CharacterDetailRepository
import javax.inject.Inject

class CharacterDetailRepositoryImpl @Inject constructor(
    private val api: CharacterDetailApi
) : CharacterDetailRepository {

    override fun getCharacterDetails(id: Int): Single<CharacterDetail> {
        return api.getCharacterDetails(id = id).map {
            CharacterDetailMapper.mapRemoteToDomain(it)
        }
    }

    override fun getMultipleEpisodes(list: String): Single<List<CharacterEpisodeDetail>> {
        return api.getMultipleEpisodes(list = list).map {
            CharacterDetailMapper.episodeListRemoteToDomain(it)
        }
    }
}