package ru.mihmas.rickandmortyapp.feature_caching.character.presentation.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.mihmas.rickandmortyapp.R
import ru.mihmas.rickandmortyapp.databinding.ItemCharacterListBinding
import ru.mihmas.rickandmortyapp.feature_caching.character.presentation.model.CachedCharacter
import javax.inject.Inject

class CachedCharacterViewHolder @Inject constructor(private val bind: ItemCharacterListBinding) : RecyclerView.ViewHolder(bind.root) {

    fun initViewHolder(item: CachedCharacter) {
        bind.nameCharacter.text = item.name
        bind.speciesCharacter.text = item.species
        bind.statusCharacter.text = item.status
        bind.genderCharacter.text = item.gender
        Glide.with(itemView).load(item.image).placeholder(R.drawable.placeholder).into(bind.imageCharacter)
    }
}