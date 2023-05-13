package ru.mihmas.rickandmortyapp.feature_details.episode.presentation.screen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.mihmas.rickandmortyapp.R
import ru.mihmas.rickandmortyapp.core.App
import ru.mihmas.rickandmortyapp.databinding.FragmentEpisodeDetailBinding
import ru.mihmas.rickandmortyapp.feature_details.character.presentation.screen.CharacterDetailFragment
import ru.mihmas.rickandmortyapp.feature_details.episode.domain.model.EpisodeCharacterDetail
import ru.mihmas.rickandmortyapp.feature_details.episode.presentation.recyclerview.ListOfEpisodeCharacterAdapter
import ru.mihmas.rickandmortyapp.feature_details.episode.presentation.screen.state.EpisodeDetailError
import ru.mihmas.rickandmortyapp.feature_details.episode.presentation.screen.state.EpisodeDetailPresentation
import ru.mihmas.rickandmortyapp.feature_details.episode.presentation.screen.state.EpisodeDetailProgress
import ru.mihmas.rickandmortyapp.feature_details.episode.presentation.screen.state.EpisodeDetailResult
import ru.mihmas.rickandmortyapp.feature_details.episode.presentation.viewmodel.EpisodeDetailViewModel
import ru.mihmas.rickandmortyapp.feature_details.episode.presentation.viewmodel.EpisodeDetailViewModelFactory
import javax.inject.Inject

class EpisodeDetailFragment : Fragment() {
    @Inject lateinit var viewModelFactory: EpisodeDetailViewModelFactory
    @Inject lateinit var charactersAdapter: ListOfEpisodeCharacterAdapter
    private val component by lazy { (requireActivity().application as App).component }
    private val viewModel by lazy { ViewModelProvider(requireActivity(), viewModelFactory)[EpisodeDetailViewModel::class.java] }
    private val bind by lazy { FragmentEpisodeDetailBinding.inflate(layoutInflater) }
    private var episodeId = UNDEFINED_ID

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            episodeId = it.getInt(EPISODE_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = bind.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bind.charactersInEpisodeRecyclerView.adapter = charactersAdapter
        viewModel.getEpisodeDetails(episodeId = episodeId)
        viewModel.state.observe(viewLifecycleOwner) {
            when(it) {
                is EpisodeDetailProgress -> {
                    bind.progressBarEpisodeDetail.visibility = View.VISIBLE
                    bind.errorEpisodeDetail.visibility = View.GONE
                    hideAllViews()
                }
                is EpisodeDetailError -> {
                    bind.progressBarEpisodeDetail.visibility = View.GONE
                    bind.errorEpisodeDetail.visibility = View.VISIBLE
                    hideAllViews()
                }
                is EpisodeDetailResult -> {
                    initViews(it.result)
                    setupListeners()
                    bind.progressBarEpisodeDetail.visibility = View.GONE
                    bind.errorEpisodeDetail.visibility = View.GONE
                    showAllViews()
                }
            }
        }
    }

    private fun initViews(model: EpisodeDetailPresentation) {
        charactersAdapter.submitList(model.characters)
        bind.nameEpisodeDetail.text = model.name
        bind.numberEpisodeDetail.text = model.episode
        bind.airDateEpisodeDetail.text = model.airDate
    }

    private fun setupListeners() {
        charactersAdapter.onCharacterClickListener = object : ListOfEpisodeCharacterAdapter.OnCharacterClickListener {
            override fun onClick(episodeCharacterDetail: EpisodeCharacterDetail) {
                parentFragmentManager
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(
                        R.id.main_fragment_fragment_container_view,
                        CharacterDetailFragment.getInstance(episodeCharacterDetail.id)
                    )
                    .commit()
            }
        }
    }

    private fun showAllViews() {
        bind.nameEpisodeDetail.visibility = View.VISIBLE
        bind.numberEpisodeDetail.visibility = View.VISIBLE
        bind.airDateEpisodeDetail.visibility = View.VISIBLE
        bind.charactersInEpisodeRecyclerView.visibility = View.VISIBLE
    }

    private fun hideAllViews() {
        bind.nameEpisodeDetail.visibility = View.GONE
        bind.numberEpisodeDetail.visibility = View.GONE
        bind.airDateEpisodeDetail.visibility = View.GONE
        bind.charactersInEpisodeRecyclerView.visibility = View.GONE
    }

    companion object {
        private const val EPISODE_ID = "EPISODE_ID"
        private const val UNDEFINED_ID = -1
        fun getInstance(episodeId: Int) : EpisodeDetailFragment = EpisodeDetailFragment().apply {
            arguments = Bundle().apply {
                putInt(EPISODE_ID, episodeId)
            }
        }
    }
}