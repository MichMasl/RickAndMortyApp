package ru.mihmas.rickandmortyapp.feature_caching.location.presentation.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.mihmas.rickandmortyapp.databinding.ItemLocationListBinding
import ru.mihmas.rickandmortyapp.feature_caching.location.presentation.model.CachedLocation
import javax.inject.Inject

class CachedLocationAdapter @Inject constructor(): ListAdapter<CachedLocation, CachedLocationViewHolder>(
    CachedLocationDiffUtil
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CachedLocationViewHolder {
        val bind = ItemLocationListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CachedLocationViewHolder(bind = bind)
    }

    override fun onBindViewHolder(holder: CachedLocationViewHolder, position: Int) {
        val item = getItem(position)
        holder.initViewHolder(item = item)
    }
}