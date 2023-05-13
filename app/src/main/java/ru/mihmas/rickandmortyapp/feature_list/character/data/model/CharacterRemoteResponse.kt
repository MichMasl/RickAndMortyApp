package ru.mihmas.rickandmortyapp.feature_list.character.data.model

data class CharacterRemoteResponse(
    val info: CharacterResponseInfo,
    val results: List<CharacterModelRemote>
)