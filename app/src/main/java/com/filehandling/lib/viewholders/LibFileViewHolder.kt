package com.filehandling.lib.viewholders

/**
 * This file is created by Lalit N. Hajare on 2/4/2020.
 */

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.filehandling.lib.R
import com.filehandling.lib.fragments.DirectoryFragment
import com.filehandling.lib.models.LibFile
import com.filehandling.lib.utils.DateFormatter
import com.filehandling.lib.utils.FileIconProvider
import java.util.*

class LibFileViewHolder(
    itemView: View,
    private var mCallback: (LibFile, DirectoryFragment.Ops) -> Unit
) :
    ViewHolder(itemView) {

    private val imgFile = itemView.findViewById<ImageView>(R.id.img_file)
    private val txtFileName = itemView.findViewById<TextView>(R.id.txt_file_name)
    private val txtFileDesc = itemView.findViewById<TextView>(R.id.txt_file_desc)
    private val imgTick = itemView.findViewById<ImageView>(R.id.img_tick)

    override fun onBindView(model: Any) {
        val file = model as LibFile

        if (file.isSelected) {
            imgTick.visibility = View.VISIBLE
        } else {
            imgTick.visibility = View.INVISIBLE
        }

        imgFile.setImageDrawable(FileIconProvider.getFileIcon(itemView.context, file.name))


        if (!file.name.isNullOrEmpty()) {
            txtFileName.text = file.name
        }
        txtFileDesc.text = file.size.toString()

        itemView.setOnClickListener {
            if (imgTick.visibility == View.INVISIBLE || imgTick.visibility == View.GONE)
                mCallback(file, DirectoryFragment.Ops.ADD)
            else
                mCallback(file, DirectoryFragment.Ops.REMOVE)
        }
    }

}