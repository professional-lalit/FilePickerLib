package com.filehandling.lib.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.filehandling.lib.R
import com.filehandling.lib.fragments.DirectoryFragment
import com.filehandling.lib.models.CustomFileModel
import com.filehandling.lib.utils.DateFormatter
import java.util.*

/**
 * This file is created by Lalit N. Hajare on 1/30/2020.
 */
class FileViewHolder(
    itemView: View,
    private var mCallback: (CustomFileModel, DirectoryFragment.Ops) -> Unit
) :
    ViewHolder(itemView) {

    private val imgFile = itemView.findViewById<ImageView>(R.id.img_file)
    private val txtFileName = itemView.findViewById<TextView>(R.id.txt_file_name)
    private val txtFileDesc = itemView.findViewById<TextView>(R.id.txt_file_desc)
    private val imgTick = itemView.findViewById<ImageView>(R.id.img_tick)

    override fun onBindView(model: Any) {
        val file = model as CustomFileModel

        if (file.isSelected) {
            imgTick.visibility = View.VISIBLE
        } else {
            imgTick.visibility = View.INVISIBLE
        }

        if (file.name.endsWith(".txt")) {
            imgFile.setImageDrawable(
                ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.ic_text
                )
            )
        } else if (file.name.endsWith(".ppt")) {
            imgFile.setImageDrawable(
                ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.ic_ppt
                )
            )
        } else if (file.name.endsWith(".pdf")) {
            imgFile.setImageDrawable(
                ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.ic_pdf
                )
            )
        } else if (file.name.endsWith(".cfg") || file.name.endsWith(".config")) {
            imgFile.setImageDrawable(
                ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.ic_config
                )
            )
        } else if (file.name.endsWith(".mp3") || file.name.endsWith(".wav") || file.name.endsWith(".mp4")
            || file.name.endsWith(".3gp")
        ) {
            imgFile.setImageDrawable(
                ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.ic_music_note
                )
            )
        } else if (file.name.endsWith(".png") || file.name.endsWith(".jpeg") || file.name.endsWith(".jpg")
            || file.name.endsWith(".giff")
        ) {
            imgFile.setImageDrawable(
                ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.ic_photo
                )
            )
        } else if (!file.isDirectory) {
            imgFile.setImageDrawable(
                ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.ic_unknown
                )
            )
        }else{
            imgFile.setImageDrawable(
                ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.ic_folder
                )
            )
        }


        if (!file.name.isNullOrEmpty()) {
            txtFileName.text = file.name
        }
        if (!file.list().isNullOrEmpty()) {
            txtFileDesc.text = "(${file.list()?.size!!})"
        } else if (file.isDirectory) {
            txtFileDesc.text = "(0)"
        } else {
            txtFileDesc.text = "Last Modified : ${DateFormatter.getStringFromDate(
                Date(file.lastModified()),
                DateFormatter.dd_MM_yyyy_HH_mm
            )}"
        }

        itemView.setOnClickListener {
            if (file.listFiles() != null && file.listFiles()?.isNotEmpty()!! && file.isDirectory) {
                mCallback(file, DirectoryFragment.Ops.NO_OP)
            } else if (!file.isDirectory) {
                if (imgTick.visibility == View.INVISIBLE || imgTick.visibility == View.GONE)
                    mCallback(file, DirectoryFragment.Ops.ADD)
                else
                    mCallback(file, DirectoryFragment.Ops.REMOVE)
            }
        }
    }

}