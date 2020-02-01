package com.filehandling.lib.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.filehandling.lib.R
import com.filehandling.lib.models.CustomFileModel

class TestActivity : AppCompatActivity() {
    private val REQ_FILES = 789

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val intent = Intent(this, FileChooserActivity::class.java)
        startActivityForResult(intent, REQ_FILES)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val fileList = ArrayList<CustomFileModel>()
            val bundle = data?.getBundleExtra("file-bundle")
            fileList.addAll(bundle?.getSerializable("file-list") as ArrayList<CustomFileModel>)
            for (file in fileList) {
                Toast.makeText(this, file.name, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
