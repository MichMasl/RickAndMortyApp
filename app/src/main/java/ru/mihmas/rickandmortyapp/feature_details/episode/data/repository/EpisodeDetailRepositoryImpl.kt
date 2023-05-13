package ru.mihmas.rickandmortyapp.feature_details.episode.data.repository

import ru.mihmas.rickandmortyapp.feature_details.episode.data.mapper.EpisodeDetailMapper
import ru.mihmas.rickandmortyapp.feature_details.episode.data.network.EpisodeDetailApi
import ru.mihmas.rickandmortyapp.feature_details.episode.domain.model.EpisodeCharacterDetail
import ru.mihmas.rickandmortyapp.feature_details.episode.domain.model.EpisodeDetail
import ru.mihmas.rickandmortyapp.feature_details.episode.domain.repository.EpisodeDetailRepository
import javax.inject.Inject

class EpisodeDetailRepositoryImpl @Inject constructor(
    private val api: EpisodeDetailApi
) : EpisodeDetailRepository {
    override suspend fun getEpisodeDetails(id: Int): EpisodeDetail {
        val model = api.getEpisodeDetails(id = id)
        return EpisodeDetailMapper.mapRemoteToDomain(model = model)
    }

    override suspend fun getMultipleCharacters(list: String): List<EpisodeCharacterDetail> {
        val epList = api.getMultipleCharacters(list = list)
        return EpisodeDetailMapper.characterListRemoteToDomain(epList)
    }
}