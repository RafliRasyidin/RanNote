package com.rasyidin.rannote.ui.dialog

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatDialog
import com.rasyidin.rannote.databinding.AddDialogBinding
import com.rasyidin.rannote.ui.feature.note.AddUpdateNoteActivity
import com.rasyidin.rannote.ui.feature.todo.AddUpdateTodoActivity

class AddDialog(context: Context) : AppCompatDialog(context) {

    private var _binding: AddDialogBinding? = null
    private val binding get() = _binding!!

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
            val intent = Intent(context, AddUpdateNoteActivity::class.java)
            context.startActivity(intent)
            dismiss()
        }

        binding.imgNote.setOnClickListener {
            val intent = Intent(context, AddUpdateNoteActivity::class.java)
            context.startActivity(intent)
            dismiss()
        }
    }

    private fun navigateToAddUpdateTodoFragment() {
        binding.tvAddTodo.setOnClickListener {
            val intent = Intent(context, AddUpdateTodoActivity::class.java)
            context.startActivity(intent)
            dismiss()
        }

        binding.imgTodo.setOnClickListener {
            val intent = Intent(context, AddUpdateTodoActivity::class.java)
            context.startActivity(intent)
            dismiss()
        }
    }

}