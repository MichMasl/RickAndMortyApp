package ru.mihmas.rickandmortyapp.feature_caching.character.presentation.screen

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
import ru.mihmas.rickandmortyapp.databinding.FragmentCharacterListBinding
import ru.mihmas.rickandmortyapp.feature_caching.character.presentation.recyclerview.CachedCharacterAdapter
import ru.mihmas.rickandmortyapp.feature_caching.character.presentation.viewmodel.CachedCharacterViewModel
import ru.mihmas.rickandmortyapp.feature_caching.character.presentation.viewmodel.CachedCharacterViewModelFactory
import ru.mihmas.rickandmortyapp.feature_list.character.presentation.screen.CharacterRemoteListFragment
import javax.inject.Inject

class CharacterLocalListFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: CachedCharacterViewModelFactory
    @Inject
    lateinit var characterAdapter: CachedCharacterAdapter
    private val component by lazy {
        (requireActivity().application as App).component
    }
    private val viewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory)[CachedCharacterViewModel::class.java]
    }
    private val bind: FragmentCharacterListBinding by lazy {
        FragmentCharacterListBinding.inflate(layoutInflater)
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
        bind.recyclerViewCharacter.adapter = characterAdapter
    }

    private fun setupObservers() {
        viewModel.getAllCharacters()
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is Progress -> {
                    bind.progressBarCharacter.visibility = View.VISIBLE
                    bind.errorCharacter.visibility = View.GONE
                    bind.retryButtonCharacter.visibility = View.GONE
                }
                is Result -> {
                    bind.progressBarCharacter.visibility = View.GONE
                    bind.errorCharacter.visibility = View.GONE
                    bind.recyclerViewCharacter.visibility = View.VISIBLE
                    bind.swipeRefreshCharacter.isRefreshing = false
                    characterAdapter.submitList(it.result)
                }
                is Error -> {
                    bind.progressBarCharacter.visibility = View.GONE
                    bind.recyclerViewCharacter.visibility = View.GONE
                    bind.errorCharacter.visibility = View.VISIBLE
                    bind.retryButtonCharacter.visibility = View.VISIBLE
                    bind.swipeRefreshCharacter.isRefreshing = false
                }
                is NoResult -> {
                    bind.progressBarCharacter.visibility = View.GONE
                    bind.recyclerViewCharacter.visibility = View.GONE
                    bind.errorCharacter.visibility = View.VISIBLE
                    bind.errorCharacter.text = requireContext().getText(R.string.not_found)
                    bind.swipeRefreshCharacter.isRefreshing = false
                }
            }
        }
    }

    private fun setupSwipeRefreshListener() {
        bind.swipeRefreshCharacter.setOnRefreshListener {
            viewModel.getAllCharacters()
            characterAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    bind.recyclerViewCharacter.scrollToPosition(0)
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
                    viewModel.findCharacter(name = name)
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
                    CharacterRemoteListFragment.getFragment()
                ).commit()
            true
        }
    }

    private fun setupFilterMenu(menu: Menu) {
        val filterView = menu.findItem(R.id.action_filter)
        filterView.setOnMenuItemClickListener {
            openCharacterFilterDialog()
            true
        }
    }

    private fun openCharacterFilterDialog() {
        CachedCharacterFilterDialog().show(
            childFragmentManager,
            FILTER_TAG
        )
    }

    private fun setFilterDialogListener() {
        childFragmentManager.setFragmentResultListener(
            CHARACTER_DIALOG_BUNDLE,
            this
        ) { _, bundle ->
            viewModel.filterCharacters(
                species = bundle.getString(
                    FILTER_SPECIES,
                    DEFAULT_VALUE
                ),
                type = bundle.getString(
                    FILTER_TYPE,
                    DEFAULT_VALUE
                ),
                status = bundle.getString(
                    FILTER_STATUS,
                    DEFAULT_VALUE
                ),
                gender = bundle.getString(
                    FILTER_GENDER,
                    DEFAULT_VALUE
                )
            )
        }
    }

    companion object {
        const val CHARACTER_DIALOG_BUNDLE = "LOCAL_CHARACTER_DIALOG_BUNDLE"
        const val FILTER_SPECIES = "FILTER_SPECIES"
        const val FILTER_TYPE = "FILTER_TYPE"
        const val FILTER_STATUS = "FILTER_STATUS"
        const val FILTER_GENDER = "FILTER_GENDER"
        private const val DEFAULT_VALUE = "DEFAULT_VALUE"
        private const val FILTER_TAG = "Local character filter dialog"

        fun getFragment(): CharacterLocalListFragment = CharacterLocalListFragment()
    }
}