package ru.mihmas.rickandmortyapp.feature_caching.location.presentation.recyclerview

import androidx.recyclerview.widget.RecyclerView
import ru.mihmas.rickandmortyapp.databinding.ItemLocationListBinding
import ru.mihmas.rickandmortyapp.feature_caching.location.presentation.model.CachedLocation
import javax.inject.Inject

class CachedLocationViewHolder @Inject constructor(private val bind: ItemLocationListBinding) : RecyclerView.ViewHolder(bind.root) {

    fun initViewHolder(item: CachedLocation) {
        bind.nameLocation.text = item.name
        bind.typeLocation.text = item.type
        bind.dimensionLocation.text = item.dimension
    }
}