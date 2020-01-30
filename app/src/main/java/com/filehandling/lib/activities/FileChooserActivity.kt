package com.filehandling.lib.activities

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.filehandling.lib.FolderViewModel
import com.filehandling.lib.R
import com.filehandling.lib.fragments.BlankFragment
import java.io.File


const val REQ_FILE_ACCESS = 232


class FileChooserActivity : AppCompatActivity() {

    private lateinit var fileContainer: FrameLayout
    private lateinit var rootDir: File
    public lateinit var mFolderViewModel: FolderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_chooser)
        rootDir = Environment.getExternalStorageDirectory()
        initActionBar()
        setViews()
        getPerms()
    }

    private fun setViews() {
        fileContainer = findViewById(R.id.file_container)
        mFolderViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(FolderViewModel::class.java)
        mFolderViewModel.mCurrentDir.postValue(rootDir)
    }


    private fun initActionBar() {
        supportActionBar?.apply {
            setHomeAsUpIndicator(R.drawable.ic_left_arrow)
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowCustomEnabled(true)
            setDisplayShowTitleEnabled(true)
        }
    }


    private fun getPerms() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(
                    arrayOf(
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                        , android.Manifest.permission.READ_EXTERNAL_STORAGE
                    ), REQ_FILE_ACCESS
                )
            } else {
                mFolderViewModel.mCurrentDir.observe(this, Observer { currentDir ->
                    addFragment(currentDir)
                })
            }
        } else {
            mFolderViewModel.mCurrentDir.observe(this, Observer { currentDir ->
                addFragment(currentDir)
            })
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (permissions.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
            && grantResults[1] == PackageManager.PERMISSION_GRANTED
        ) {
            mFolderViewModel.mCurrentDir.observe(this, Observer { currentDir ->
                addFragment(currentDir)
            })
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                    arrayOf(
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                        , android.Manifest.permission.READ_EXTERNAL_STORAGE
                    ), REQ_FILE_ACCESS
                )
            }
        }
    }

    private fun addFragment(currentDir: File) {
        val dirFragment = BlankFragment()
        dirFragment.arguments = Bundle()
        dirFragment.arguments?.putSerializable("dir", currentDir)
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
            .add(fileContainer.id, dirFragment)
            .addToBackStack(currentDir.name)
            .commit()
        supportActionBar?.title = currentDir.name
    }

    private fun popFragment() {
        supportFragmentManager.popBackStack()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (supportFragmentManager.backStackEntryCount == 1)
                finish()
            else
                popFragment()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
