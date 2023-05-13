package ru.mihmas.rickandmortyapp.mapper_test

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.mihmas.rickandmortyapp.feature_details.character.data.mapper.CharacterDetailMapper
import ru.mihmas.rickandmortyapp.feature_details.character.data.model.CharacterDetailRemote
import ru.mihmas.rickandmortyapp.feature_details.character.data.model.CharacterEpisodeDetailRemote
import ru.mihmas.rickandmortyapp.feature_details.character.data.model.CharacterReferenceRemote
import ru.mihmas.rickandmortyapp.feature_details.character.domain.model.CharacterDetail
import ru.mihmas.rickandmortyapp.feature_details.character.domain.model.CharacterEpisodeDetail

private const val TEST_DATA = "TEST"

class DetailCharacterRemoteToDomainMapperTest {
    private lateinit var characterDetailRemote: CharacterDetailRemote
    private lateinit var characterDetail: CharacterDetail
    private lateinit var characterEpisodeDetailListRemote: List<CharacterEpisodeDetailRemote>
    private lateinit var characterEpisodeDetailListDomain: List<CharacterEpisodeDetail>

    @Before
    fun init() {
        val referenceRemote = CharacterReferenceRemote(
            name = TEST_DATA,
            url = TEST_DATA
        )
        characterDetailRemote = CharacterDetailRemote(
            created = TEST_DATA,
            episode = listOf(TEST_DATA),
            gender = TEST_DATA,
            id = 1,
            image = TEST_DATA,
            location = referenceRemote,
            name = TEST_DATA,
            origin = referenceRemote,
            species = TEST_DATA,
            status = TEST_DATA,
            type = TEST_DATA,
            url = TEST_DATA
        )
        characterDetail = CharacterDetail(
            id = 1,
            name = TEST_DATA,
            status = TEST_DATA,
            species = TEST_DATA,
            type = TEST_DATA,
            gender = TEST_DATA,
            originName = TEST_DATA,
            originUrl = TEST_DATA,
            locationName = TEST_DATA,
            locationUrl = TEST_DATA,
            image = TEST_DATA,
            episode = listOf(TEST_DATA)
        )
        characterEpisodeDetailListRemote = listOf(
            CharacterEpisodeDetailRemote(
                air_date = TEST_DATA,
                characters = listOf(TEST_DATA),
                episode = TEST_DATA,
                id = 1,
                name = TEST_DATA,
                created = TEST_DATA,
                url = TEST_DATA
            )
        )
        characterEpisodeDetailListDomain = listOf(
            CharacterEpisodeDetail(
                id = 1,
                name = TEST_DATA,
                episode = TEST_DATA,
                airDate = TEST_DATA
            )
        )
    }

    @Test
    fun should_return_CharacterDetail() {
        val result = CharacterDetailMapper.mapRemoteToDomain(characterDetailRemote)
        assertEquals(characterDetail, result)
    }

    @Test
    fun should_return_CharacterEpisodeDetail() {
        val result = CharacterDetailMapper.episodeListRemoteToDomain(characterEpisodeDetailListRemote)
        assertEquals(characterEpisodeDetailListDomain, result)
    }
}