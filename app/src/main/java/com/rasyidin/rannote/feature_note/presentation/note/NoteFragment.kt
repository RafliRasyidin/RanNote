package com.rasyidin.rannote.feature_note.presentation.note

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rasyidin.rannote.R
import com.rasyidin.rannote.databinding.FragmentNoteBinding
import com.rasyidin.rannote.core.di.OnBoardingPreference
import com.rasyidin.rannote.feature_note.domain.util.SortUtils
import com.rasyidin.rannote.feature_note.presentation.detail_note.DetailNoteFragment
import com.rasyidin.rannote.ui.adapter.note.NoteAdapter
import com.rasyidin.rannote.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@FlowPreview
@AndroidEntryPoint
class NoteFragment : BaseFragment<FragmentNoteBinding>(FragmentNoteBinding::inflate) {

    @Inject
    lateinit var pref: OnBoardingPreference

    private val viewModel: NoteViewModel by viewModels()

    @Inject
    lateinit var noteAdapter: NoteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

        observeNotes()

        setUsername()

        searchNote()

        navigateToDetail()

        sortNotes()
    }

    override fun onResume() {
        super.onResume()

        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).supportActionBar?.title = null
        val navBar =
            (activity as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bot_nav_view)
        navBar.visibility = View.VISIBLE

        val appBar = (activity as AppCompatActivity).findViewById<BottomAppBar>(R.id.bot_app_bar)
        appBar.visibility = View.VISIBLE

        val fabAdd =
            (activity as AppCompatActivity).findViewById<FloatingActionButton>(R.id.fab_add)
        fabAdd.visibility = View.VISIBLE
    }

    private fun setupAdapter() = binding.rvNote.apply {
        layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = noteAdapter
        setOnScrollChangeListener { view, i, i2, i3, i4 ->

        }
    }

    @SuppressLint("SetTextI18n")
    private fun setUsername() {
        val username = pref.getUserOnBoardingPref().name
        binding.tvHiUser.text = "Hi, $username!"
    }

    private fun sortNotes() {
        var isOrderByDateDesc = false
        var isOrderByDateAsc = true
        var isOrderByTitleAsc = false
        var isOrderByTitleDesc = false
        binding.imgSort.setOnClickListener {
            when {
                isOrderByDateDesc -> {
                    viewModel.getNotes(SortUtils.DATE_DESC)
                    isOrderByDateDesc = false
                    isOrderByDateAsc = true
                    isOrderByTitleAsc = false
                    isOrderByTitleDesc = false
                }
                isOrderByDateAsc -> {
                    viewModel.getNotes(SortUtils.DATE_ASC)
                    isOrderByDateDesc = false
                    isOrderByDateAsc = false
                    isOrderByTitleAsc = true
                    isOrderByTitleDesc = false
                }
                isOrderByTitleAsc -> {
                    viewModel.getNotes(SortUtils.TITLE_ASC)
                    isOrderByDateDesc = false
                    isOrderByDateAsc = false
                    isOrderByTitleAsc = false
                    isOrderByTitleDesc = true
                }
                isOrderByTitleDesc -> {
                    viewModel.getNotes(SortUtils.TITLE_DESC)
                    isOrderByDateDesc = true
                    isOrderByDateAsc = false
                    isOrderByTitleAsc = false
                    isOrderByTitleDesc = false
                }

            }
        }
    }

    private fun navigateToDetail() {
        noteAdapter.onItemClickListener = { note ->
            val args = Bundle().apply {
                putParcelable(DetailNoteFragment.ARG_NOTE, note)
            }
            findNavController().navigate(R.id.action_noteFragment_to_detailNoteFragment, args)
        }
    }

    private fun observeNotes() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.notes.collectLatest { pagingData ->
                lifecycleScope.launchWhenCreated {
                    noteAdapter.submitData(pagingData)
                }
            }
        }
    }

    private fun searchNote() {
        binding.svNote.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                    viewModel.getNotes(SortUtils.DATE_DESC)
                } else {
                    viewModel.searchNotes("%$newText%")
                }
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding?.rvNote?.adapter = null
        _binding = null
    }

    companion object {
        private val TAG = NoteFragment::class.simpleName
    }
}