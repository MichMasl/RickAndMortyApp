package ru.mihmas.rickandmortyapp.feature_caching.character.presentation.screen

import ru.mihmas.rickandmortyapp.feature_caching.character.presentation.model.CachedCharacter
import javax.inject.Inject

sealed class CharacterCacheState

object Progress : CharacterCacheState()
object Error : CharacterCacheState()
object NoResult : CharacterCacheState()
class Result @Inject constructor(val result: List<CachedCharacter>) : CharacterCacheState()