package ru.mihmas.rickandmortyapp.feature_list.location.presentation.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.mihmas.rickandmortyapp.databinding.ItemLocationListBinding
import ru.mihmas.rickandmortyapp.feature_list.location.domain.model.LocationModel
import javax.inject.Inject

class LocationAdapter @Inject constructor(): ListAdapter<LocationModel, LocationViewHolder>(
    LocationDiffUtil
) {
    var onReachEndListener: (() -> Unit)? = null
    var onItemClickListener: (() -> Unit)? = null
    var clickedItemId = UNKNOWN_ID

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val bind = ItemLocationListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LocationViewHolder(bind = bind)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val item = getItem(position)
        holder.initViewHolder(item = item)
        setListeners(position = position, holder = holder)
    }

    private fun setListeners(position: Int, holder: LocationViewHolder) {
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