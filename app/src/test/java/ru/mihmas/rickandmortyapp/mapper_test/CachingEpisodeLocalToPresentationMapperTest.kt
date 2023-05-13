package ru.mihmas.rickandmortyapp.mapper_test

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import ru.mihmas.rickandmortyapp.feature_caching.episode.data.mapper.EpisodeLocalMapper
import ru.mihmas.rickandmortyapp.feature_caching.episode.data.model.EpisodeLocal
import ru.mihmas.rickandmortyapp.feature_caching.episode.presentation.model.CachedEpisode

private const val TEST_DATA = "TEST_DATA"

class CachingEpisodeLocalToPresentationMapperTest {
    private lateinit var episodeLocalList: List<EpisodeLocal>
    private lateinit var cachedEpisodeList: List<CachedEpisode>

    @Before
    fun init() {
        episodeLocalList = listOf(
            EpisodeLocal(
                id = 1,
                name = TEST_DATA,
                episode = TEST_DATA,
                airDate = TEST_DATA
            )
        )
        cachedEpisodeList = listOf(
            CachedEpisode(
                id = 1,
                name = TEST_DATA,
                episode = TEST_DATA,
                airDate = TEST_DATA
            )
        )
    }

    @Test
    fun should_return_CachedEpisode() {
        val result = EpisodeLocalMapper(episodeLocalList).map()
        Assert.assertEquals(cachedEpisodeList, result)
    }
}