package com.filehandling.lib.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.filehandling.lib.R
import com.filehandling.lib.ui.main.HomeActivity
import com.filehandling.lib.utils.beginActivityForResult
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity() {
    private val REQ_FILES = 789

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        btn_choose_files.setOnClickListener {
            beginActivityForResult(HomeActivity::class.java, REQ_FILES)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val uriList = ArrayList<Uri>()
            uriList.addAll(data?.getSerializableExtra("uri-list") as ArrayList<Uri>)
            var fileNames = ""
            for (uri in uriList) {
                var path = uri.toString()
                fileNames += "Path: [$path] \n\n"
            }
            txt_files.text = fileNames
        }
    }
}
