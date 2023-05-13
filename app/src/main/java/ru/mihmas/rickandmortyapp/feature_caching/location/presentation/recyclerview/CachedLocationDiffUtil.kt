package ru.mihmas.rickandmortyapp.feature_caching.location.presentation.recyclerview

import androidx.recyclerview.widget.DiffUtil
import ru.mihmas.rickandmortyapp.feature_caching.location.presentation.model.CachedLocation

object CachedLocationDiffUtil : DiffUtil.ItemCallback<CachedLocation>() {
    override fun areItemsTheSame(oldItem: CachedLocation, newItem: CachedLocation): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CachedLocation, newItem: CachedLocation): Boolean {
        return oldItem == newItem
    }
}