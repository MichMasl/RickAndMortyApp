package ru.mihmas.rickandmortyapp.feature_list.episode.presentation.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.mihmas.rickandmortyapp.databinding.ItemEpisodeListBinding
import ru.mihmas.rickandmortyapp.feature_list.episode.domain.model.EpisodeModel
import javax.inject.Inject

class EpisodeAdapter @Inject constructor(): ListAdapter<EpisodeModel, EpisodeViewHolder>(
    EpisodeDiffUtil
) {
    var onReachEndListener: (() -> Unit)? = null
    var onItemClickListener: (() -> Unit)? = null
    var clickedItemId = UNKNOWN_ID

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val bind = ItemEpisodeListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EpisodeViewHolder(bind = bind)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val item = getItem(position)
        holder.initViewHolder(item = item)
        setListeners(position = position, holder = holder)
    }

    private fun setListeners(position: Int, holder: EpisodeViewHolder) {
        holder.itemView.setOnClickListener {
            clickedItemId = holder.getId()
            onItemClickListener?.invoke()
        }
        if (position == currentList.size - 4 && onReachEndListener != null) {
            onReachEndListener?.invoke()
        }
    }

    companion object {
        const val UNKNOWN_ID = -1
    }
}