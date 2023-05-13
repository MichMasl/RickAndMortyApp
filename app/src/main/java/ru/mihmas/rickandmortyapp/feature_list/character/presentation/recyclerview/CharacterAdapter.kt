package ru.mihmas.rickandmortyapp.feature_list.character.presentation.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.mihmas.rickandmortyapp.databinding.ItemCharacterListBinding
import ru.mihmas.rickandmortyapp.feature_list.character.domain.model.CharacterModel
import javax.inject.Inject

class CharacterAdapter @Inject constructor(): ListAdapter<CharacterModel, CharacterViewHolder>(
    CharacterDiffUtil
) {
    var onReachEndListener: (() -> Unit)? = null
    var onItemClickListener: (() -> Unit)? = null
    var clickedItemId = UNKNOWN_ID

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val bind = ItemCharacterListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterViewHolder(bind = bind)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = getItem(position)
        holder.initViewHolder(item = item)
        setListeners(position = position, holder = holder)
    }

    private fun setListeners(position: Int, holder: CharacterViewHolder) {
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