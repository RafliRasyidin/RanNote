package com.rasyidin.rannote.ui.adapter.note

import android.graphics.Color
import com.rasyidin.rannote.R
import com.rasyidin.rannote.feature_note.domain.model.ColorPicker
import com.rasyidin.rannote.databinding.ItemListColorBinding
import com.rasyidin.rannote.ui.adapter.BaseAdapter

class ColorAdapter : BaseAdapter<ColorPicker>(R.layout.item_list_color) {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = data[position]
        val binding = ItemListColorBinding.bind(holder.itemView)
        with(binding) {
            cardColor.setCardBackgroundColor(Color.parseColor(item.colorCard))
            view1.setCardBackgroundColor(Color.parseColor(item.colorDesc))
            view2.setCardBackgroundColor(Color.parseColor(item.colorDesc))
            view3.setCardBackgroundColor(Color.parseColor(item.colorDesc))

            root.setOnClickListener {
                onItemClickListener?.invoke(item)
            }
        }
    }
}