package com.filehandling.lib.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.filehandling.lib.R
import java.io.File

/**
 * This file is created by Lalit N. Hajare on 1/30/2020.
 */
class FileViewHolder(itemView: View, private var mCallback: (File) -> Unit) : ViewHolder(itemView) {

    private val imgFile = itemView.findViewById<ImageView>(R.id.img_file)
    private val txtFileName = itemView.findViewById<TextView>(R.id.txt_file_name)
    private val txtFileDesc = itemView.findViewById<TextView>(R.id.txt_file_desc)

    override fun onBindView(model: Any) {
        val file = model as File

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
        }


        if (!file.name.isNullOrEmpty()) {
            txtFileName.text = file.name
        }
        if (!file.list().isNullOrEmpty()) {
            txtFileDesc.text = "(${file.list().size})"
        } else {
            txtFileDesc.text = "(0)"
        }

        itemView.setOnClickListener {
            if (file.listFiles() != null && file.listFiles().isNotEmpty())
                mCallback(file)
        }
    }

}