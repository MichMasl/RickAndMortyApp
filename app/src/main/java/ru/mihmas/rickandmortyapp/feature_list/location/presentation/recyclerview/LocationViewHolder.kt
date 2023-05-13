package ru.mihmas.rickandmortyapp.feature_list.location.presentation.recyclerview

import androidx.recyclerview.widget.RecyclerView
import ru.mihmas.rickandmortyapp.databinding.ItemLocationListBinding
import ru.mihmas.rickandmortyapp.feature_list.location.domain.model.LocationModel
import javax.inject.Inject

class LocationViewHolder @Inject constructor(
    private val bind: ItemLocationListBinding
) : RecyclerView.ViewHolder(bind.root) {
    private var id = LocationAdapter.UNKNOWN_ID

    fun initViewHolder(item: LocationModel) {
        id = item.id
        bind.nameLocation.text = item.name
        bind.typeLocation.text = item.type
        bind.dimensionLocation.text = item.dimension
    }

    fun getId(): Int = id
}