package ru.mihmas.rickandmortyapp.feature_list.episode.data.datasource

import ru.mihmas.rickandmortyapp.feature_caching.episode.data.local.EpisodeDao
import ru.mihmas.rickandmortyapp.feature_list.episode.data.mapper.EpisodeRemoteToDomainMapper
import ru.mihmas.rickandmortyapp.feature_list.episode.data.mapper.EpisodeRemoteToLocalMapper
import ru.mihmas.rickandmortyapp.feature_list.episode.data.model.EpisodeModelRemote
import ru.mihmas.rickandmortyapp.feature_list.episode.data.network.EpisodeListApi
import ru.mihmas.rickandmortyapp.feature_list.episode.domain.model.EpisodeModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EpisodeListProvider @Inject constructor(
    private val remote: EpisodeListApi,
    private val dao : EpisodeDao
) {
    private val episodeList = mutableListOf<EpisodeModelRemote>()
    private var nextPage: String? = DEFAULT_VALUE
    private var page = FIRST_PAGE

    suspend fun getListOfEpisodes(
        name: String,
        episode: String
    ) : List<EpisodeModel> {
        if (nextPage != null) {
            val response = remote.getListOfEpisodes(
                page = page,
                name = name,
                episode = episode
            )
            nextPage = response.info.next
            episodeList.addAll(response.results)
            page++
        }
        dao.putListOfEpisodes(EpisodeRemoteToLocalMapper(episodeList).map())
        return EpisodeRemoteToDomainMapper(episodeList).map()
    }

    fun refreshListOfEpisodes() {
        episodeList.clear()
        nextPage = DEFAULT_VALUE
        page = FIRST_PAGE
    }

    companion object {
        private const val DEFAULT_VALUE = "defaultValue"
        private const val FIRST_PAGE = 1
    }
}