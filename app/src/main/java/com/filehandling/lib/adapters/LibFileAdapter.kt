package com.filehandling.lib.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.filehandling.lib.R
import com.filehandling.lib.fragments.DirectoryFragment
import com.filehandling.lib.models.CustomFileModel
import com.filehandling.lib.models.LibFile
import com.filehandling.lib.viewholders.FileViewHolder
import com.filehandling.lib.viewholders.LibFileViewHolder
import com.filehandling.lib.viewholders.ViewHolder

/**
 * This file is created by Lalit N. Hajare on 2/4/2020.
 */
class LibFileAdapter(
    private var mList: ArrayList<LibFile>,
    private var mCallback: (LibFile, DirectoryFragment.Ops) -> Unit
) :
    RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LibFileViewHolder(
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