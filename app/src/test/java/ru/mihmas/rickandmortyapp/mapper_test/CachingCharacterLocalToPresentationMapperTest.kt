package ru.mihmas.rickandmortyapp.mapper_test

import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import ru.mihmas.rickandmortyapp.feature_caching.character.data.mapper.CharacterLocalMapper
import ru.mihmas.rickandmortyapp.feature_caching.character.data.model.CharacterLocal
import ru.mihmas.rickandmortyapp.feature_caching.character.presentation.model.CachedCharacter

private const val TEST_DATA = "TEST_DATA"

class CachingCharacterLocalToPresentationMapperTest {
    private lateinit var characterLocalList: List<CharacterLocal>
    private lateinit var cachedCharacterList: List<CachedCharacter>

    @Before
    fun init() {
        characterLocalList = listOf(
            CharacterLocal(
                id = 1,
                name = TEST_DATA,
                species = TEST_DATA,
                type = TEST_DATA,
                status = TEST_DATA,
                gender = TEST_DATA,
                image = TEST_DATA
            )
        )
        cachedCharacterList = listOf(
            CachedCharacter(
                id = 1,
                name = TEST_DATA,
                species = TEST_DATA,
                type = TEST_DATA,
                status = TEST_DATA,
                gender = TEST_DATA,
                image = TEST_DATA
            )
        )
    }

    @Test
    fun should_return_CachedCharacter() {
        val result = CharacterLocalMapper(characterLocalList).map()
        assertEquals(cachedCharacterList, result)
    }
}