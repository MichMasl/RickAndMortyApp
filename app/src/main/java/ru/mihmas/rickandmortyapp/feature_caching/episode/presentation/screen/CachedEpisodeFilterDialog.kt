package ru.mihmas.rickandmortyapp.feature_caching.episode.presentation.screen

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputLayout
import ru.mihmas.rickandmortyapp.R

class CachedEpisodeFilterDialog  : DialogFragment() {
    private lateinit var episodeEditText: TextInputLayout

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = requireActivity().layoutInflater.inflate(R.layout.filter_episode, null)
        episodeEditText = view.findViewById(R.id.episode_episode_text_field)

        return AlertDialog.Builder(requireContext())
            .setView(view)
            .setTitle("Filter episode")
            .setPositiveButton("Apply filters") { _, _ ->
                val episode = episodeEditText.editText?.text.toString()
                parentFragmentManager.setFragmentResult(
                    EpisodeLocalListFragment.EPISODE_DIALOG_BUNDLE,
                    bundleOf(
                        EpisodeLocalListFragment.FILTER_EPISODE to episode,
                    )
                )
            }
            .setNeutralButton("Cancel") { _, _ -> }
            .create()
    }
}