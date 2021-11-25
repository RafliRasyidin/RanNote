package com.rasyidin.rannote.ui.feature.note

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
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
import com.rasyidin.rannote.core.utils.onFailure
import com.rasyidin.rannote.core.utils.onSuccess
import com.rasyidin.rannote.databinding.FragmentNoteBinding
import com.rasyidin.rannote.di.OnBoardingPreference
import com.rasyidin.rannote.ui.adapter.note.NoteAdapter
import com.rasyidin.rannote.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import javax.inject.Inject

@FlowPreview
@AndroidEntryPoint
class NoteFragment : BaseFragment<FragmentNoteBinding>(FragmentNoteBinding::inflate) {

    @Inject
    lateinit var pref: OnBoardingPreference

    private val viewModel: NoteViewModel by viewModels()

    private val noteAdapter: NoteAdapter by lazy {
        NoteAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

        subscribeToObserver()

        setUsername()

        searchNote()

        navigateToDetail()

        sortNotes()

        observeSearchedNotes()

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
                    subscribeToObserver()
                    isOrderByDateDesc = false
                    isOrderByDateAsc = true
                    isOrderByTitleAsc = false
                    isOrderByTitleDesc = false
                }
                isOrderByDateAsc -> {
                    viewModel.getAllNotesOrderByDateAsc()
                    lifecycleScope.launchWhenCreated {
                        viewModel.listNotesOrderByDateAsc.collect { resultState ->
                            resultState.onSuccess { resultData ->
                                lifecycleScope.launchWhenCreated {
                                    noteAdapter.submitData(resultData)
                                }
                            }
                            resultState.onFailure {
                                Toast.makeText(requireActivity(), it.message, Toast.LENGTH_SHORT)
                                    .show()
                                Log.e("Error", "Error ${it.message}")
                            }
                        }
                    }
                    isOrderByDateDesc = false
                    isOrderByDateAsc = false
                    isOrderByTitleAsc = true
                    isOrderByTitleDesc = false
                }
                isOrderByTitleAsc -> {
                    viewModel.getAllNotesOrderByTitleAsc()
                    lifecycleScope.launchWhenCreated {
                        viewModel.listNotesOrderByTitleAsc.collect { resultState ->
                            resultState.onSuccess { resultData ->
                                lifecycleScope.launchWhenCreated {
                                    noteAdapter.submitData(resultData)
                                }
                            }
                            resultState.onFailure {
                                Toast.makeText(requireActivity(), it.message, Toast.LENGTH_SHORT)
                                    .show()
                                Log.e("Error", "Error ${it.message}")
                            }
                        }
                    }
                    isOrderByDateDesc = false
                    isOrderByDateAsc = false
                    isOrderByTitleAsc = false
                    isOrderByTitleDesc = true
                }
                isOrderByTitleDesc -> {
                    viewModel.getAllNotesOrderByTitleDesc()
                    lifecycleScope.launchWhenCreated {
                        viewModel.listNotesOrderByTitleDesc.collect { resultState ->
                            resultState.onSuccess { resultData ->
                                lifecycleScope.launchWhenCreated {
                                    noteAdapter.submitData(resultData)
                                }
                            }
                            resultState.onFailure {
                                Toast.makeText(requireActivity(), it.message, Toast.LENGTH_SHORT)
                                    .show()
                                Log.e("Error", "Error ${it.message}")
                            }
                        }
                    }
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

    private fun subscribeToObserver() {
        viewModel.getAllNotesOrderByDateDesc()
        lifecycleScope.launchWhenCreated {
            viewModel.listNotesOrderByDateDesc
                .collect { resultState ->
                    resultState.onSuccess { resultData ->
                        lifecycleScope.launchWhenCreated {
                            noteAdapter.submitData(resultData)
                        }
                    }
                    resultState.onFailure {
                        Toast.makeText(requireActivity(), it.message, Toast.LENGTH_SHORT).show()
                        Log.e(TAG, "Error ${it.message}")
                    }
                }
        }
    }

    private fun observeSearchedNotes() {
        lifecycleScope.launchWhenCreated {
            viewModel.searchNotes
                .debounce(500)
                .collect { resultState ->
                    resultState.onSuccess { data ->
                        lifecycleScope.launchWhenCreated {
                            noteAdapter.submitData(data)
                        }
                    }

                    resultState.onFailure {
                        Toast.makeText(requireActivity(), it.message, Toast.LENGTH_SHORT).show()
                        Log.e(TAG, "Error ${it.message}")
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
                    subscribeToObserver()
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