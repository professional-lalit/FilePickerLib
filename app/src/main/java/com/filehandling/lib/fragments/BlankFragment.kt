package com.filehandling.lib.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.filehandling.lib.FolderViewModel

import com.filehandling.lib.R
import com.filehandling.lib.activities.MainActivity
import com.filehandling.lib.adapters.FileAdapter
import java.io.File

/**
 * A simple [Fragment] subclass.
 */
class BlankFragment : Fragment() {

    private lateinit var mDir: File
    private lateinit var mAdapter: FileAdapter
    private lateinit var recyclerFiles: RecyclerView
    private val mFileList = arrayListOf<File>()

    private lateinit var mFolderViewModel: FolderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mFolderViewModel = (activity as MainActivity).mFolderViewModel
        mDir = arguments?.getSerializable("dir") as File
        setAdapter(view)
        getDirectories()
    }

    private fun setAdapter(view: View) {
        recyclerFiles = view.findViewById(R.id.recycler_files)
        recyclerFiles.layoutManager = LinearLayoutManager(context)
        mAdapter = FileAdapter(mFileList) { file ->
            mFolderViewModel.mCurrentDir.postValue(file)
        }
        recyclerFiles.adapter = mAdapter
    }


    private fun getDirectories() {
        if (mDir.exists() && mDir.listFiles() != null && mDir.listFiles()!!.isNotEmpty()) {
            mFileList.addAll(mDir.listFiles()!!)
            mAdapter.notifyDataSetChanged()
        } else {
            Toast.makeText(context, "Folder does not exists", Toast.LENGTH_SHORT).show()
        }
    }


}
