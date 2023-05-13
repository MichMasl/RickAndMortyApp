package ru.mihmas.rickandmortyapp.feature_list.character.presentation.screen

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.mihmas.rickandmortyapp.R
import ru.mihmas.rickandmortyapp.core.App
import ru.mihmas.rickandmortyapp.databinding.FragmentCharacterListBinding
import ru.mihmas.rickandmortyapp.feature_caching.character.presentation.screen.CharacterLocalListFragment
import ru.mihmas.rickandmortyapp.feature_details.character.presentation.screen.CharacterDetailFragment
import ru.mihmas.rickandmortyapp.feature_list.character.presentation.recyclerview.CharacterAdapter
import ru.mihmas.rickandmortyapp.feature_list.character.presentation.viewmodel.CharacterListViewModel
import ru.mihmas.rickandmortyapp.feature_list.character.presentation.viewmodel.CharacterListViewModelFactory
import javax.inject.Inject

class CharacterRemoteListFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: CharacterListViewModelFactory
    @Inject
    lateinit var characterAdapter: CharacterAdapter
    private val component by lazy {
        (requireActivity().application as App).component
    }
    private val viewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory)[CharacterListViewModel::class.java]
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
        setupListeners()
        setupObservers()
    }

    private fun setupAdapter() {
        bind.recyclerViewCharacter.adapter = characterAdapter
    }

    private fun setupListeners() {
        setupPullToRefreshListener()
        setupRetryButtonListener()
        setupEndOfScrollListListener()
        setupItemClickListener()
    }

    private fun setupObservers() {
        viewModel.characterState.observe(viewLifecycleOwner) {
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

    private fun setupPullToRefreshListener() {
        bind.swipeRefreshCharacter.setOnRefreshListener {
            viewModel.refreshListOfCharacters()
        }
    }

    private fun setupRetryButtonListener() {
        bind.retryButtonCharacter.setOnClickListener {
            viewModel.refreshListOfCharacters()
            bind.recyclerViewCharacter.scrollToPosition(0)
        }
    }

    private fun setupEndOfScrollListListener() {
        characterAdapter.onReachEndListener = {
            viewModel.getListOfCharacters()
        }
    }

    private fun setupItemClickListener() {
        characterAdapter.onItemClickListener = {
            val clickedItemId = characterAdapter.clickedItemId
            parentFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(
                    R.id.main_fragment_fragment_container_view,
                    CharacterDetailFragment.getInstance(
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
                    viewModel.findCharactersByName(name = name)
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
        cacheView.icon = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_toolbar_local)
        cacheView.setOnMenuItemClickListener {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.main_fragment_fragment_container_view,
                    CharacterLocalListFragment.getFragment()
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
        CharacterFilterDialog().show(
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
                species = bundle.getString(FILTER_SPECIES, DEFAULT_VALUE),
                type = bundle.getString(FILTER_TYPE, DEFAULT_VALUE),
                status = bundle.getString(FILTER_STATUS, DEFAULT_VALUE),
                gender = bundle.getString(FILTER_GENDER, DEFAULT_VALUE)
            )
        }
    }

    companion object {
        const val CHARACTER_DIALOG_BUNDLE = "CHARACTER_DIALOG_BUNDLE"
        const val FILTER_SPECIES = "FILTER_SPECIES"
        const val FILTER_TYPE = "FILTER_TYPE"
        const val FILTER_STATUS = "FILTER_STATUS"
        const val FILTER_GENDER = "FILTER_GENDER"
        private const val DEFAULT_VALUE = "DEFAULT_VALUE"
        private const val FILTER_TAG = "Character filter dialog"

        fun getFragment(): CharacterRemoteListFragment = CharacterRemoteListFragment()
    }
}