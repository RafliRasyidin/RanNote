package com.rasyidin.rannote.ui.adapter.note

import androidx.recyclerview.widget.DiffUtil
import com.rasyidin.rannote.R
import com.rasyidin.rannote.core.domain.model.note.Note
import com.rasyidin.rannote.databinding.ItemNoteBinding
import com.rasyidin.rannote.ui.adapter.BasePagingAdapter

class NoteAdapter: BasePagingAdapter<Note>(R.layout.item_note, DiffCallback)  {

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        val note = getItem(position)
        val binding = ItemNoteBinding.bind(holder.itemView)
        with(binding) {
            note?.let { note ->
                tvTitle.text = note.title
                tvDesc.text = note.desc
                tvDate.text = note.date
                cardNote.setBackgroundColor(note.color.toInt())
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Note>() {

        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }
}