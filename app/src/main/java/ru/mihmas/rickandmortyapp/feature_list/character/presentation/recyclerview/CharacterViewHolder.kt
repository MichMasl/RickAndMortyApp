package ru.mihmas.rickandmortyapp.feature_list.character.presentation.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.mihmas.rickandmortyapp.R
import ru.mihmas.rickandmortyapp.databinding.ItemCharacterListBinding
import ru.mihmas.rickandmortyapp.feature_list.character.domain.model.CharacterModel
import javax.inject.Inject

class CharacterViewHolder @Inject constructor(private val bind: ItemCharacterListBinding) : RecyclerView.ViewHolder(bind.root) {
    private var id = CharacterAdapter.UNKNOWN_ID

    fun initViewHolder(item: CharacterModel) {
        id = item.id
        bind.nameCharacter.text = item.name
        bind.speciesCharacter.text = item.species
        bind.statusCharacter.text = item.status
        bind.genderCharacter.text = item.gender
        Glide.with(itemView).load(item.image).placeholder(R.drawable.placeholder).into(bind.imageCharacter)
    }

    fun getId(): Int = id
}