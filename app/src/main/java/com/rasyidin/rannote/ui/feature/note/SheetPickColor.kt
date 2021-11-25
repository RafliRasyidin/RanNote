package com.rasyidin.rannote.ui.feature.note

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rasyidin.rannote.core.domain.model.note.ColorPicker
import com.rasyidin.rannote.databinding.SheetPickColorBinding
import com.rasyidin.rannote.ui.adapter.note.ColorAdapter
import java.util.*

class SheetPickColor : BottomSheetDialogFragment() {

    private var _binding: SheetPickColorBinding? = null
    private val binding get() = _binding!!

    private val colorAdapter: ColorAdapter by lazy { ColorAdapter() }

    private lateinit var colors: List<ColorPicker>

    private val noteColorPicker = ColorPicker()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SheetPickColorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            colors = arguments?.getParcelableArrayList(ARG_COLORS)!!
            setupRv()

            selectThemeColor()
        }

    }

    var onItemCLickCallback: ((ColorPicker) -> Unit)? = null

    private fun setupRv() = binding.rvColor.apply {
        adapter = colorAdapter
        layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        colorAdapter.setData(colors)
    }

    private fun selectThemeColor() {
        colorAdapter.onItemClickListener = { colorPicker ->
            onItemCLickCallback?.invoke(colorPicker)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val ARG_COLORS = "colors"

        val TAG = SheetPickColor::class.simpleName

        fun newInstance(colors: List<ColorPicker>) =
            SheetPickColor().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(
                        ARG_COLORS,
                        colors as ArrayList<out Parcelable?>?
                    )
                }
            }
    }


}