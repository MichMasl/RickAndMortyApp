package ru.mihmas.rickandmortyapp.feature_details.episode.presentation.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.mihmas.rickandmortyapp.databinding.ItemCharacterListBinding
import ru.mihmas.rickandmortyapp.feature_details.episode.domain.model.EpisodeCharacterDetail
import javax.inject.Inject

class ListOfEpisodeCharacterAdapter @Inject constructor() : ListAdapter<EpisodeCharacterDetail, ListOfEpisodeCharacterViewHolder>(ListOfEpisodeCharacterDiffUtil) {

    var onCharacterClickListener: OnCharacterClickListener? = null

    interface OnCharacterClickListener {
        fun onClick(episodeCharacterDetail: EpisodeCharacterDetail)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListOfEpisodeCharacterViewHolder {
        val bind = ItemCharacterListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ListOfEpisodeCharacterViewHolder(bind = bind)
    }

    override fun onBindViewHolder(holder: ListOfEpisodeCharacterViewHolder, position: Int) {
        val item = getItem(position)
        holder.initView(item = item)
        holder.itemView.setOnClickListener { onCharacterClickListener?.onClick(item) }
    }
}