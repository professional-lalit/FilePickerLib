package com.filehandling.lib.ui.main

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import com.filehandling.lib.R
import com.filehandling.lib.ui.explorer.FileChooserActivity
import com.filehandling.lib.models.CustomFileModel
import com.filehandling.lib.models.LibFile
import com.filehandling.lib.utils.beginActivityForResult

class HomeActivity : AppCompatActivity() {

    private val REQ_FILE_EXPLORER = 78
    lateinit var mPageViewModel: PageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mPageViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(PageViewModel::class.java)

        initActionBar()
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            val intent = Intent()

            val uriList = arrayListOf<Uri>()
            mPageViewModel.fileList.value?.forEach { file ->
                uriList.add(file.uri)
            }
            intent.putExtra("uri-list", uriList)
            setResult(Activity.RESULT_OK, intent)
            finish()
            return true
        } else if (item.itemId == R.id.action_folder_search) {
            beginActivityForResult(FileChooserActivity::class.java, REQ_FILE_EXPLORER)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_FILE_EXPLORER) {
            if (resultCode == Activity.RESULT_OK) {
                val fileList = ArrayList<CustomFileModel>()
                val bundle = data?.getBundleExtra("file-bundle")
                fileList.addAll(bundle?.getSerializable("file-list") as ArrayList<CustomFileModel>)
                for (file in fileList) {
                    val libFile = LibFile(file.toUri(), true, file.name, 0, 0)
                    if (!mPageViewModel.fileList.value!!.contains(libFile))
                        mPageViewModel.addFile(libFile)
                }
                setResult(resultCode)
            }
        }
    }
}