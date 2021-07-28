package com.rasyidin.rannote.ui.feature.note.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatDialog
import com.rasyidin.rannote.core.domain.model.note.Note
import com.rasyidin.rannote.databinding.AddDialogBinding

class AddDialog(context: Context, private var onClickListener: AddDialogListener) :
    AppCompatDialog(context) {

    private var _binding: AddDialogBinding? = null
    private val binding get() = _binding!!
    private val note: Note by lazy { Note() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        _binding = AddDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setLayout(700, ViewGroup.LayoutParams.WRAP_CONTENT)

        navigateToAddUpdateNoteFragment()

        navigateToAddUpdateTodoFragment()

    }

    private fun navigateToAddUpdateNoteFragment() {
        binding.tvAddNote.setOnClickListener {
            onClickListener.navigateToAddDetailNote(note)
            dismiss()
        }

        binding.imgNote.setOnClickListener {
            onClickListener.navigateToAddDetailNote(note)
            dismiss()
        }
    }

    private fun navigateToAddUpdateTodoFragment() {
        binding.tvAddTodo.setOnClickListener {
            onClickListener.navigateToAddDetailTodo()
            dismiss()
        }

        binding.imgTodo.setOnClickListener {
            onClickListener.navigateToAddDetailTodo()
            dismiss()
        }
    }

}

interface AddDialogListener {
    fun navigateToAddDetailNote(note: Note)
    fun navigateToAddDetailTodo()
}