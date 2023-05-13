package ru.mihmas.rickandmortyapp.mapper_test

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.mihmas.rickandmortyapp.feature_details.episode.data.mapper.EpisodeDetailMapper
import ru.mihmas.rickandmortyapp.feature_details.episode.data.model.EpisodeCharacterDetailRemote
import ru.mihmas.rickandmortyapp.feature_details.episode.data.model.EpisodeDetailRemote
import ru.mihmas.rickandmortyapp.feature_details.episode.data.model.EpisodeReferenceRemote
import ru.mihmas.rickandmortyapp.feature_details.episode.domain.model.EpisodeCharacterDetail
import ru.mihmas.rickandmortyapp.feature_details.episode.domain.model.EpisodeDetail

private const val TEST_DATA = "TEST_DATA"

class DetailEpisodeRemoteToDomainMapperTest {
    private lateinit var episodeDetailRemote: EpisodeDetailRemote
    private lateinit var episodeDetail: EpisodeDetail
    private lateinit var episodeCharacterDetailListRemote: List<EpisodeCharacterDetailRemote>
    private lateinit var episodeCharacterDetailListDomain: List<EpisodeCharacterDetail>

    @Before
    fun init() {
        val reference = EpisodeReferenceRemote(
            name = TEST_DATA,
            url = TEST_DATA
        )
        episodeDetailRemote = EpisodeDetailRemote(
            air_date = TEST_DATA,
            characters = listOf(TEST_DATA),
            created = TEST_DATA,
            episode = TEST_DATA,
            id = 1,
            name = TEST_DATA,
            url = TEST_DATA
        )
        episodeDetail = EpisodeDetail(
            id = 1,
            name = TEST_DATA,
            airDate = TEST_DATA,
            episode = TEST_DATA,
            characters = listOf(TEST_DATA)
        )
        episodeCharacterDetailListRemote = listOf(
            EpisodeCharacterDetailRemote(
                created = TEST_DATA,
                episode = listOf(TEST_DATA),
                gender = TEST_DATA,
                id = 1,
                image = TEST_DATA,
                location = reference,
                name = TEST_DATA,
                origin = reference,
                species = TEST_DATA,
                status = TEST_DATA,
                type = TEST_DATA,
                url = TEST_DATA
            )
        )
        episodeCharacterDetailListDomain = listOf(
            EpisodeCharacterDetail(
                id = 1,
                name = TEST_DATA,
                species = TEST_DATA,
                status = TEST_DATA,
                gender = TEST_DATA,
                image = TEST_DATA
            )
        )
    }

    @Test
    fun should_return_EpisodeDetail() {
        val result = EpisodeDetailMapper.mapRemoteToDomain(episodeDetailRemote)
        assertEquals(episodeDetail, result)
    }

    @Test
    fun should_return_EpisodeCharacterDetail() {
        val result = EpisodeDetailMapper.characterListRemoteToDomain(episodeCharacterDetailListRemote)
        assertEquals(episodeCharacterDetailListDomain, result)
    }
}