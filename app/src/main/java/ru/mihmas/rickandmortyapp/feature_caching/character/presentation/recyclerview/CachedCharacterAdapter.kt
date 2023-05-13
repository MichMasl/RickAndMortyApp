package ru.mihmas.rickandmortyapp.feature_caching.character.presentation.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.mihmas.rickandmortyapp.databinding.ItemCharacterListBinding
import ru.mihmas.rickandmortyapp.feature_caching.character.presentation.model.CachedCharacter
import javax.inject.Inject

class CachedCharacterAdapter @Inject constructor(): ListAdapter<CachedCharacter, CachedCharacterViewHolder>(
    CachedCharacterDiffUtil
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CachedCharacterViewHolder {
        val bind = ItemCharacterListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CachedCharacterViewHolder(bind = bind)
    }

    override fun onBindViewHolder(holder: CachedCharacterViewHolder, position: Int) {
        val item = getItem(position)
        holder.initViewHolder(item = item)
    }
}