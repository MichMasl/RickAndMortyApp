package ru.mihmas.rickandmortyapp.feature_details.episode.presentation.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.mihmas.rickandmortyapp.databinding.ItemCharacterListBinding
import ru.mihmas.rickandmortyapp.feature_details.episode.domain.model.EpisodeCharacterDetail
import javax.inject.Inject

class ListOfEpisodeCharacterViewHolder @Inject constructor(
    private val bind: ItemCharacterListBinding
) : RecyclerView.ViewHolder(bind.root) {

    fun initView(item: EpisodeCharacterDetail) {
        bind.nameCharacter.text = item.name
        bind.speciesCharacter.text = item.species
        bind.statusCharacter.text = item.status
        bind.genderCharacter.text = item.gender
        Glide.with(itemView).load(item.image).into(bind.imageCharacter)
    }
}