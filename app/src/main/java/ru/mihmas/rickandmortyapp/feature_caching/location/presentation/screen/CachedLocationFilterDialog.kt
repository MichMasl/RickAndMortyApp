package ru.mihmas.rickandmortyapp.feature_caching.location.presentation.screen

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputLayout
import ru.mihmas.rickandmortyapp.R

class CachedLocationFilterDialog  : DialogFragment() {
    private lateinit var typeEditText: TextInputLayout
    private lateinit var dimensionEditText: TextInputLayout

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = requireActivity().layoutInflater.inflate(R.layout.filter_location, null)
        typeEditText = view.findViewById(R.id.type_location_text_field)
        dimensionEditText = view.findViewById(R.id.dimension_location_text_field)

        return AlertDialog.Builder(requireContext())
            .setView(view)
            .setTitle("Filter episode")
            .setPositiveButton("Apply filters") { _, _ ->
                val type = typeEditText.editText?.text.toString()
                val dimension = dimensionEditText.editText?.text.toString()
                parentFragmentManager.setFragmentResult(
                    LocationLocalListFragment.LOCATION_DIALOG_BUNDLE,
                    bundleOf(
                        LocationLocalListFragment.FILTER_TYPE to type,
                        LocationLocalListFragment.FILTER_DIMENSION to dimension
                    )
                )
            }
            .setNeutralButton("Cancel") { _, _ -> }
            .create()
    }
}