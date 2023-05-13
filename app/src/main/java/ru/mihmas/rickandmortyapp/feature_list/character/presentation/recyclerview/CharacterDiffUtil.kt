package ru.mihmas.rickandmortyapp.feature_list.character.presentation.recyclerview

import androidx.recyclerview.widget.DiffUtil
import ru.mihmas.rickandmortyapp.feature_list.character.domain.model.CharacterModel

object CharacterDiffUtil : DiffUtil.ItemCallback<CharacterModel>() {
    override fun areItemsTheSame(
        oldItem: CharacterModel,
        newItem: CharacterModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: CharacterModel,
        newItem: CharacterModel
    ): Boolean {
        return oldItem == newItem
    }
}