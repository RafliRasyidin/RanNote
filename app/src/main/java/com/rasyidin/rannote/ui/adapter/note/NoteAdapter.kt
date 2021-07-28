package com.rasyidin.rannote.ui.adapter.note

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.recyclerview.widget.DiffUtil
import com.rasyidin.rannote.R
import com.rasyidin.rannote.core.domain.model.note.Note
import com.rasyidin.rannote.databinding.ItemNoteBinding
import com.rasyidin.rannote.ui.adapter.BasePagingAdapter

class NoteAdapter : BasePagingAdapter<Note>(R.layout.item_note, DiffCallback) {

    @SuppressLint("Range")
    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        val note = getItem(position)
        val binding = ItemNoteBinding.bind(holder.itemView)
        with(binding) {
            note?.let { note ->
                tvTitle.text = note.title
                tvTitle.setTextColor(Color.parseColor(if (note.colorTitle == "") "#b84f4f" else note.colorTitle))

                tvDesc.text = note.desc
                tvDesc.setTextColor(Color.parseColor(if (note.colorDesc == "") "#c24c4c" else note.colorDesc))

                tvDate.text = note.date
                tvDate.setTextColor(Color.parseColor(if (note.colorDesc == "") "#c24c4c" else note.colorDesc))

                cardNote.setCardBackgroundColor(Color.parseColor(if (note.colorCard == "") "#ffadad" else note.colorCard))


                root.setOnClickListener {
                    onItemClickListener?.invoke(note)
                }
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