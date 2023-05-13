package ru.mihmas.rickandmortyapp.mapper_test

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import ru.mihmas.rickandmortyapp.feature_caching.episode.data.model.EpisodeLocal
import ru.mihmas.rickandmortyapp.feature_list.episode.data.mapper.EpisodeRemoteToLocalMapper
import ru.mihmas.rickandmortyapp.feature_list.episode.data.model.EpisodeModelRemote

class ListEpisodeRemoteToLocalMapperTest {
    private lateinit var episodeListRemote: List<EpisodeModelRemote>
    private lateinit var episodeListLocal: List<EpisodeLocal>
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
        episodeListLocal = listOf(
            EpisodeLocal(
                id = 1,
                name = "test",
                episode = "test",
                airDate = "test"
            )
        )
    }

    @Test
    fun should_return_EpisodeLocal() {
        val result = EpisodeRemoteToLocalMapper(episodeListRemote).map()
        Assert.assertEquals(episodeListLocal, result)
    }
}