package ru.mihmas.rickandmortyapp.feature_caching.character.presentation.screen

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputLayout
import ru.mihmas.rickandmortyapp.R

class CachedCharacterFilterDialog  : DialogFragment() {
    private lateinit var speciesEditText: TextInputLayout
    private lateinit var typeEditText: TextInputLayout
    private lateinit var statusEditText: TextInputLayout
    private lateinit var genderEditText: TextInputLayout

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = requireActivity().layoutInflater.inflate(R.layout.filter_character, null)
        speciesEditText = view.findViewById(R.id.species_character_text_field)
        typeEditText = view.findViewById(R.id.type_character_text_field)
        statusEditText = view.findViewById(R.id.status_character_text_field)
        genderEditText = view.findViewById(R.id.gender_character_text_field)

        return AlertDialog.Builder(requireContext())
            .setView(view)
            .setTitle("Filter character")
            .setPositiveButton("Apply filters") { _, _ ->
                val species = speciesEditText.editText?.text.toString()
                val type = typeEditText.editText?.text.toString()
                val status = statusEditText.editText?.text.toString()
                val gender = genderEditText.editText?.text.toString()
                parentFragmentManager.setFragmentResult(
                    CharacterLocalListFragment.CHARACTER_DIALOG_BUNDLE,
                    bundleOf(
                        CharacterLocalListFragment.FILTER_SPECIES to species,
                        CharacterLocalListFragment.FILTER_TYPE to type,
                        CharacterLocalListFragment.FILTER_STATUS to status,
                        CharacterLocalListFragment.FILTER_GENDER to gender
                    )
                )
            }
            .setNeutralButton("Cancel") { _, _ -> }
            .create()
    }
}