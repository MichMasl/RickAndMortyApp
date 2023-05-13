package ru.mihmas.rickandmortyapp.feature_list.episode.presentation.screen

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.mihmas.rickandmortyapp.R
import ru.mihmas.rickandmortyapp.core.App
import ru.mihmas.rickandmortyapp.databinding.FragmentEpisodeListBinding
import ru.mihmas.rickandmortyapp.feature_caching.episode.presentation.screen.EpisodeLocalListFragment
import ru.mihmas.rickandmortyapp.feature_details.episode.presentation.screen.EpisodeDetailFragment
import ru.mihmas.rickandmortyapp.feature_list.episode.presentation.recyclerview.EpisodeAdapter
import ru.mihmas.rickandmortyapp.feature_list.episode.presentation.viewmodel.EpisodeListViewModel
import ru.mihmas.rickandmortyapp.feature_list.episode.presentation.viewmodel.EpisodeListViewModelFactory
import javax.inject.Inject

class EpisodeRemoteListFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: EpisodeListViewModelFactory
    @Inject
    lateinit var episodeAdapter: EpisodeAdapter
    private val component by lazy {
        (requireActivity().application as App).component
    }
    private val viewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory)[EpisodeListViewModel::class.java]
    }

    private val bind: FragmentEpisodeListBinding by lazy {
        FragmentEpisodeListBinding.inflate(layoutInflater)
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        setFilterDialogListener()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        setupSearchMenu(menu = menu)
        setupCacheMenu(menu = menu)
        setupFilterMenu(menu = menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = bind.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupAdapter()
        setupListeners()
        setupObservers()
    }

    private fun setupAdapter() {
        bind.recyclerViewEpisode.adapter = episodeAdapter
    }

    private fun setupListeners() {
        setupPullToRefreshListener()
        setupRetryButtonListener()
        setupEndOfScrollListener()
        setupItemClickListener()
    }

    private fun setupObservers() {
        viewModel.episodeState.observe(viewLifecycleOwner) {
            when (it) {
                is Progress -> {
                    bind.progressBarEpisode.visibility = View.VISIBLE
                    bind.errorEpisode.visibility = View.GONE
                    bind.retryButtonEpisode.visibility = View.GONE
                }
                is Result -> {
                    bind.progressBarEpisode.visibility = View.GONE
                    bind.errorEpisode.visibility = View.GONE
                    bind.recyclerViewEpisode.visibility = View.VISIBLE
                    bind.swipeRefreshEpisode.isRefreshing = false
                    episodeAdapter.submitList(it.result)
                }
                is Error -> {
                    bind.progressBarEpisode.visibility = View.GONE
                    bind.recyclerViewEpisode.visibility = View.GONE
                    bind.errorEpisode.visibility = View.VISIBLE
                    bind.retryButtonEpisode.visibility = View.VISIBLE
                    bind.swipeRefreshEpisode.isRefreshing = false
                }
                is NoResult -> {
                    bind.progressBarEpisode.visibility = View.GONE
                    bind.recyclerViewEpisode.visibility = View.GONE
                    bind.errorEpisode.visibility = View.VISIBLE
                    bind.errorEpisode.text = requireContext().getText(R.string.not_found)
                    bind.swipeRefreshEpisode.isRefreshing = false
                }
            }
        }
    }

    private fun setupPullToRefreshListener() {
        bind.swipeRefreshEpisode.setOnRefreshListener {
            viewModel.refreshBundleOfEpisodes()
        }
    }

    private fun setupRetryButtonListener() {
        bind.retryButtonEpisode.setOnClickListener {
            viewModel.refreshBundleOfEpisodes()
            bind.recyclerViewEpisode.scrollToPosition(0)
        }
    }

    private fun setupEndOfScrollListener() {
        episodeAdapter.onReachEndListener = {
            viewModel.getListOfEpisodes()
        }
    }

    private fun setupItemClickListener() {
        episodeAdapter.onItemClickListener = {
            val clickedItemId = episodeAdapter.clickedItemId
            parentFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(
                    R.id.main_fragment_fragment_container_view,
                    EpisodeDetailFragment.getInstance(
                        clickedItemId
                    )
                )
                .commit()
        }
    }

    private fun setupSearchMenu(menu: Menu) {
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.apply {
            queryHint = "Type here to search"
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(name: String): Boolean {
                    viewModel.findEpisodesByName(name = name)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
    }

    private fun setupCacheMenu(menu: Menu) {
        val cacheView = menu.findItem(R.id.action_cache)
        cacheView.icon = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_toolbar_local)
        cacheView.setOnMenuItemClickListener {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.main_fragment_fragment_container_view,
                    EpisodeLocalListFragment.getFragment()
                ).commit()
            true
        }
    }

    private fun setupFilterMenu(menu: Menu) {
        val filterView = menu.findItem(R.id.action_filter)
        filterView.setOnMenuItemClickListener {
            openEpisodeFilterDialog()
            true
        }
    }

    private fun openEpisodeFilterDialog() {
        EpisodeFilterDialog().show(
            childFragmentManager,
            FILTER_TAG
        )
    }

    private fun setFilterDialogListener() {
        childFragmentManager.setFragmentResultListener(
            EPISODE_DIALOG_BUNDLE,
            this
        ) { _, bundle ->
            viewModel.filterEpisodes(
                episode = bundle.getString(FILTER_EPISODE, DEFAULT_VALUE)
            )
        }
    }

    companion object {
        const val EPISODE_DIALOG_BUNDLE = "EPISODE_DIALOG_BUNDLE"
        const val FILTER_EPISODE = "FILTER_EPISODE"
        private const val DEFAULT_VALUE = "DEFAULT_VALUE"
        private const val FILTER_TAG = "Episode filter dialog"

        fun getFragment(): EpisodeRemoteListFragment = EpisodeRemoteListFragment()
    }
}