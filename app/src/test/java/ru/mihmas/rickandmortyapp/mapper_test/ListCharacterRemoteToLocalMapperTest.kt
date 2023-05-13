package ru.mihmas.rickandmortyapp.mapper_test

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import ru.mihmas.rickandmortyapp.feature_caching.character.data.model.CharacterLocal
import ru.mihmas.rickandmortyapp.feature_list.character.data.mapper.CharacterRemoteToLocalMapper
import ru.mihmas.rickandmortyapp.feature_list.character.data.model.CharacterModelRemote
import ru.mihmas.rickandmortyapp.feature_list.character.data.model.CharacterReference

class ListCharacterRemoteToLocalMapperTest {
    private lateinit var characterListRemote: List<CharacterModelRemote>
    private lateinit var characterListLocal: List<CharacterLocal>

    @Before
    fun listInit() {
        val reference = CharacterReference(
            name = "test",
            url = "test"
        )
        characterListRemote = listOf(
            CharacterModelRemote(
                created = "test",
                episode = listOf("test"),
                gender = "test",
                id = 1,
                image = "test",
                location = reference,
                name = "test",
                origin = reference,
                species = "test",
                status = "test",
                type = "test",
                url = "test"
            )
        )
        characterListLocal = listOf(
            CharacterLocal(
                id = 1,
                name = "test",
                species = "test",
                status = "test",
                gender = "test",
                type = "test",
                image = "test"
            )
        )
    }

    @Test
    fun should_return_CharacterLocal() {
        val result = CharacterRemoteToLocalMapper(characterListRemote).map()
        Assert.assertEquals(characterListLocal, result)
    }
}