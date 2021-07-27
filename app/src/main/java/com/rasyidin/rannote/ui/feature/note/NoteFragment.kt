package com.rasyidin.rannote.ui.feature.note

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rasyidin.rannote.R
import com.rasyidin.rannote.databinding.FragmentNoteBinding
import com.rasyidin.rannote.di.OnBoardingPreference
import com.rasyidin.rannote.ui.adapter.note.NoteAdapter
import com.rasyidin.rannote.ui.base.BaseFragment
import com.rasyidin.rannote.ui.feature.note.AddUpdateNoteActivity.Companion.NOTE
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NoteFragment : BaseFragment<FragmentNoteBinding>(FragmentNoteBinding::inflate) {

    @Inject
    lateinit var pref: OnBoardingPreference

    private val viewModel: NoteVIewModel by viewModels()

    private lateinit var noteAdapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = null
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

        setUsername()

        searchNote()

        subscribeToObserver()

        navigateToDetail()
    }

    override fun onResume() {
        super.onResume()
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
        noteAdapter = NoteAdapter()
        layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = noteAdapter
        setHasFixedSize(true)
    }

    @SuppressLint("SetTextI18n")
    private fun setUsername() {
        val username = pref.getUserOnBoardingPref().name
        binding.tvHiUser.text = "Hi, $username!"
    }

    private fun navigateToDetail() {
        noteAdapter.onItemClickListener = { note ->
            val args = Bundle().apply {
                putParcelable(NOTE, note)
            }
            findNavController().navigate(R.id.action_noteFragment_to_addUpdateNoteActivity, args)
        }
    }

    private fun subscribeToObserver() {
        viewModel.getAllNotes.observe(viewLifecycleOwner) { notes ->
            lifecycleScope.launchWhenCreated {
                noteAdapter.submitData(notes)
            }
        }
    }

    private fun searchNote() {
        /*binding.svNote.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.searchNotes(newText).observe(viewLifecycleOwner) {

                }
            }
        })*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding?.rvNote?.adapter = null
    }
}