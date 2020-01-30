package com.filehandling.lib.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.filehandling.lib.R
import kotlinx.android.synthetic.main.item_file_layout.view.*

/**
 * This file is created by Lalit N. Hajare on 1/30/2020.
 */

abstract class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun onBindView(model: Any)
}