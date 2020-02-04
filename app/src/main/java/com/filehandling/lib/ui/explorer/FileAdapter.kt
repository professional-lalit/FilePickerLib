package com.filehandling.lib.ui.explorer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.filehandling.lib.R
import com.filehandling.lib.models.CustomFileModel
import com.filehandling.lib.common.ViewHolder

/**
 * This file is created by Lalit N. Hajare on 1/30/2020.
 */
class FileAdapter(
    private var mList: ArrayList<CustomFileModel>,
    private var mCallback: (CustomFileModel, DirectoryFragment.Ops) -> Unit
) :
    RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return FileViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_file_layout,
                null,
                false
            ), mCallback
        )
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindView(mList[position])
    }

}