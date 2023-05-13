package ru.mihmas.rickandmortyapp.feature_caching.location.presentation.screen

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import ru.mihmas.rickandmortyapp.R
import ru.mihmas.rickandmortyapp.core.App
import ru.mihmas.rickandmortyapp.databinding.FragmentLocationListBinding
import ru.mihmas.rickandmortyapp.feature_caching.location.presentation.recyclerview.CachedLocationAdapter
import ru.mihmas.rickandmortyapp.feature_caching.location.presentation.viewmodel.CachedLocationViewModel
import ru.mihmas.rickandmortyapp.feature_caching.location.presentation.viewmodel.CachedLocationViewModelFactory
import ru.mihmas.rickandmortyapp.feature_list.location.presentation.screen.LocationRemoteListFragment
import javax.inject.Inject

class LocationLocalListFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: CachedLocationViewModelFactory
    @Inject
    lateinit var locationAdapter: CachedLocationAdapter
    private val component by lazy {
        (requireActivity().application as App).component
    }
    private val viewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory)[CachedLocationViewModel::class.java]
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
        setupObservers()
        setupSwipeRefreshListener()
    }

    private fun setupAdapter() {
        bind.recyclerViewLocation.adapter = locationAdapter
    }

    private fun setupObservers() {
        viewModel.getAllLocations()
        viewModel.state.observe(viewLifecycleOwner) {
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

    private fun setupSwipeRefreshListener() {
        bind.swipeRefreshLocation.setOnRefreshListener {
            viewModel.getAllLocations()
            locationAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    bind.recyclerViewLocation.scrollToPosition(0)
                }
            })
        }
    }

    private fun setupSearchMenu(menu: Menu) {
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.apply {
            queryHint = "Type here to search"
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(name: String): Boolean {
                    viewModel.findLocation(name = name)
                    return true
                }

                override fun onQueryTextChange(name: String): Boolean {
                    return false
                }
            })
        }
    }

    private fun setupCacheMenu(menu: Menu) {
        val cacheView = menu.findItem(R.id.action_cache)
        cacheView.icon = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_toolbar_remote)
        cacheView.setOnMenuItemClickListener {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.main_fragment_fragment_container_view,
                    LocationRemoteListFragment.getFragment()
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
        CachedLocationFilterDialog().show(
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
               type = bundle.getString(
                    FILTER_TYPE,
                    DEFAULT_VALUE
                ),
                dimension = bundle.getString(
                    FILTER_DIMENSION,
                    DEFAULT_VALUE
                )
            )
        }
    }

    companion object {
        const val LOCATION_DIALOG_BUNDLE = "LOCAL_LOCATION_DIALOG_BUNDLE"
        const val FILTER_TYPE = "FILTER_TYPE"
        const val FILTER_DIMENSION = "FILTER_DIMENSION"
        private const val DEFAULT_VALUE = "DEFAULT_VALUE"
        private const val FILTER_TAG = "Local location filter dialog"

        fun getFragment(): LocationLocalListFragment = LocationLocalListFragment()
    }
}