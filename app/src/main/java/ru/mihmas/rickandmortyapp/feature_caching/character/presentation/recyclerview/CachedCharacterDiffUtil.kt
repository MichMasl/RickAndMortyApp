package ru.mihmas.rickandmortyapp.feature_caching.character.presentation.recyclerview

import androidx.recyclerview.widget.DiffUtil
import ru.mihmas.rickandmortyapp.feature_caching.character.presentation.model.CachedCharacter

object CachedCharacterDiffUtil : DiffUtil.ItemCallback<CachedCharacter>() {
    override fun areItemsTheSame(oldItem: CachedCharacter, newItem: CachedCharacter): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CachedCharacter, newItem: CachedCharacter): Boolean {
        return oldItem == newItem
    }
}