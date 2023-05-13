package ru.mihmas.rickandmortyapp.core

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.mihmas.rickandmortyapp.R
import ru.mihmas.rickandmortyapp.databinding.FragmentMainBinding
import ru.mihmas.rickandmortyapp.feature_list.character.presentation.screen.CharacterRemoteListFragment
import ru.mihmas.rickandmortyapp.feature_list.episode.presentation.screen.EpisodeRemoteListFragment
import ru.mihmas.rickandmortyapp.feature_list.location.presentation.screen.LocationRemoteListFragment

class MainFragment : Fragment() {
    private val bind by lazy { FragmentMainBinding.inflate(layoutInflater) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = bind.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews()
        setupListeners()
    }

    private fun setupViews() {
        bind.bottomNavigationView.selectedItemId = R.id.menu_characters
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_fragment_container_view, CharacterRemoteListFragment.getFragment())
            .commit()
    }

    private fun setupListeners() {
        bind.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_characters -> {
                    transactProperFragment(fragment = CharacterRemoteListFragment.getFragment())
                    true
                }
                R.id.menu_episodes -> {
                    transactProperFragment(fragment = EpisodeRemoteListFragment.getFragment())
                    true
                }
                R.id.menu_locations -> {
                    transactProperFragment(fragment = LocationRemoteListFragment.getFragment())
                    true
                }
                else -> {
                    throw RuntimeException("Unknown fragment")
                }
            }
        }
    }

    private fun transactProperFragment(fragment: Fragment) {
        parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_fragment_container_view, fragment)
            .commit()
    }

    companion object {
        fun getMainFragment(): MainFragment = MainFragment()
    }
}