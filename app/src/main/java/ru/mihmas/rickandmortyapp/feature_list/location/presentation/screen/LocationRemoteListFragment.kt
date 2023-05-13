package ru.mihmas.rickandmortyapp.feature_list.location.presentation.screen

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.mihmas.rickandmortyapp.R
import ru.mihmas.rickandmortyapp.core.App
import ru.mihmas.rickandmortyapp.databinding.FragmentLocationListBinding
import ru.mihmas.rickandmortyapp.feature_caching.location.presentation.screen.LocationLocalListFragment
import ru.mihmas.rickandmortyapp.feature_details.location.presentation.screen.LocationDetailFragment
import ru.mihmas.rickandmortyapp.feature_list.location.presentation.recyclerview.LocationAdapter
import ru.mihmas.rickandmortyapp.feature_list.location.presentation.viewmodel.LocationListViewModel
import ru.mihmas.rickandmortyapp.feature_list.location.presentation.viewmodel.LocationListViewModelFactory
import javax.inject.Inject

class LocationRemoteListFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: LocationListViewModelFactory
    @Inject
    lateinit var locationAdapter: LocationAdapter
    private val component by lazy {
        (requireActivity().application as App).component
    }
    private val viewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory)[LocationListViewModel::class.java]
    }
    private val bind: FragmentLocationListBinding by lazy {
        FragmentLocationListBinding.inflate(layoutInflater)
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
        bind.recyclerViewLocation.adapter = locationAdapter
    }

    private fun setupListeners() {
        setupPullToRefreshListener()
        setupRetryButtonListener()
        setupEndOfScrollListener()
        setupItemClickListener()
    }

    private fun setupObservers() {
        viewModel.locationState.observe(viewLifecycleOwner) {
            when (it) {
                is Progress -> {
                    bind.progressBarLocation.visibility = View.VISIBLE
                    bind.errorLocation.visibility = View.GONE
                    bind.retryButtonLocation.visibility = View.GONE
                }
                is Result -> {
                    bind.progressBarLocation.visibility = View.GONE
                    bind.errorLocation.visibility = View.GONE
                    bind.recyclerViewLocation.visibility = View.VISIBLE
                    bind.swipeRefreshLocation.isRefreshing = false
                    locationAdapter.submitList(it.result)
                }
                is Error -> {
                    bind.progressBarLocation.visibility = View.GONE
                    bind.recyclerViewLocation.visibility = View.GONE
                    bind.errorLocation.visibility = View.VISIBLE
                    bind.retryButtonLocation.visibility = View.VISIBLE
                    bind.swipeRefreshLocation.isRefreshing = false
                }
                is NoResult -> {
                    bind.progressBarLocation.visibility = View.GONE
                    bind.recyclerViewLocation.visibility = View.GONE
                    bind.errorLocation.visibility = View.VISIBLE
                    bind.errorLocation.text = requireContext().getText(R.string.not_found)
                    bind.swipeRefreshLocation.isRefreshing = false
                }
            }
        }
    }

    private fun setupPullToRefreshListener() {
        bind.swipeRefreshLocation.setOnRefreshListener {
            viewModel.refreshListOfLocations()
        }
    }

    private fun setupRetryButtonListener() {
        bind.retryButtonLocation.setOnClickListener {
            viewModel.refreshListOfLocations()
            bind.recyclerViewLocation.scrollToPosition(0)
        }
    }

    private fun setupEndOfScrollListener() {
        locationAdapter.onReachEndListener = {
            viewModel.getListOfLocations()
        }
    }

    private fun setupItemClickListener() {
        locationAdapter.onItemClickListener = {
            val clickedItemId = locationAdapter.clickedItemId
            parentFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(
                    R.id.main_fragment_fragment_container_view,
                    LocationDetailFragment.getInstance(
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
                    viewModel.findLocationsByName(name = name)
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
                    LocationLocalListFragment.getFragment()
                ).commit()
            true
        }
    }

    private fun setupFilterMenu(menu: Menu) {
        val filterView = menu.findItem(R.id.action_filter)
        filterView.setOnMenuItemClickListener {
            openLocationFilterDialog()
            true
        }
    }

    private fun openLocationFilterDialog() {
        LocationFilterDialog().show(
            childFragmentManager,
            FILTER_TAG
        )
    }

    private fun setFilterDialogListener() {
        childFragmentManager.setFragmentResultListener(
            LOCATION_DIALOG_BUNDLE,
            this
        ) { _, bundle ->
            viewModel.filterLocations(
                type = bundle.getString(FILTER_TYPE, DEFAULT_VALUE),
                dimension = bundle.getString(FILTER_DIMENSION, DEFAULT_VALUE)
            )
        }
    }

    companion object {
        const val LOCATION_DIALOG_BUNDLE = "LOCATION_DIALOG_BUNDLE"
        const val FILTER_TYPE = "FILTER_TYPE"
        const val FILTER_DIMENSION = "FILTER_DIMENSION"
        private const val DEFAULT_VALUE = "DEFAULT_VALUE"
        private const val FILTER_TAG = "Location filter dialog"

        fun getFragment(): LocationRemoteListFragment = LocationRemoteListFragment()
    }
}