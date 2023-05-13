package ru.mihmas.rickandmortyapp.mapper_test

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.mihmas.rickandmortyapp.feature_list.character.data.mapper.CharacterRemoteToDomainMapper
import ru.mihmas.rickandmortyapp.feature_list.character.data.model.CharacterModelRemote
import ru.mihmas.rickandmortyapp.feature_list.character.data.model.CharacterReference
import ru.mihmas.rickandmortyapp.feature_list.character.domain.model.CharacterModel

class ListCharacterRemoteToDomainMapperTest {
    private lateinit var characterListRemote: List<CharacterModelRemote>
    private lateinit var characterListDomain: List<CharacterModel>

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
        characterListDomain = listOf(
            CharacterModel(
                id = 1,
                name = "test",
                species = "test",
                status = "test",
                gender = "test",
                image = "test"
            )
        )
    }

    @Test
    fun should_return_CharacterModel() {
        val result = CharacterRemoteToDomainMapper(characterListRemote).map()
        assertEquals(characterListDomain, result)
    }
}