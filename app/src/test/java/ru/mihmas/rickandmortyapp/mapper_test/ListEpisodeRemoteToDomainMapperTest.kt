package ru.mihmas.rickandmortyapp.mapper_test

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import ru.mihmas.rickandmortyapp.feature_list.episode.data.mapper.EpisodeRemoteToDomainMapper
import ru.mihmas.rickandmortyapp.feature_list.episode.data.model.EpisodeModelRemote
import ru.mihmas.rickandmortyapp.feature_list.episode.domain.model.EpisodeModel

class ListEpisodeRemoteToDomainMapperTest {
    private lateinit var episodeListRemote: List<EpisodeModelRemote>
    private lateinit var episodeListDomain: List<EpisodeModel>
    @Before
    fun listInit() {
        episodeListRemote = listOf(
            EpisodeModelRemote(
                air_date = "test",
                characters = listOf("test"),
                created = "test",
                episode = "test",
                id = 1,
                name = "test",
                url = "test"
            )
        )
        episodeListDomain = listOf(
            EpisodeModel(
                id = 1,
                name = "test",
                episode = "test",
                airDate = "test"
            )
        )
    }

    @Test
    fun should_return_EpisodeModel() {
        val result = EpisodeRemoteToDomainMapper(episodeListRemote).map()
        Assert.assertEquals(episodeListDomain, result)
    }
}