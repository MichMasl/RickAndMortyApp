package ru.mihmas.rickandmortyapp.feature_list.character.presentation.screen

import ru.mihmas.rickandmortyapp.feature_list.character.domain.model.CharacterModel
import javax.inject.Inject

sealed class CharacterState

object Progress : CharacterState()
object Error : CharacterState()
object NoResult : CharacterState()
class Result @Inject constructor(val result: List<CharacterModel>) : CharacterState()