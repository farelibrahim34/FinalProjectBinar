package com.finpro.garudanih.view.profile

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.DatePicker
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.finpro.garudanih.MainActivity
import com.finpro.garudanih.R
import com.finpro.garudanih.databinding.ActivityProfileBinding
import com.finpro.garudanih.view.HomeBottomActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProfileBinding

    private val REQUEST_CODE_PERMISSION = 100
    private var imageUri: Uri? = Uri.EMPTY

    var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val adapter = ArrayAdapter.createFromResource(this,R.array.Choose_City, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        binding.PilihKota.adapter = adapter

        binding.tvTgllahir.text = "--/--/----"
        // create an OnDateSetListener
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }

        }
        binding.ivCalender.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(this@ProfileActivity,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }

        })
        binding.ivBack.setOnClickListener {
            startActivity(Intent(this, HomeBottomActivity::class.java))
        }

        binding.ivSetImage.setOnClickListener {
            checkingPermissions()
        }
        binding.btnSimpan.setOnClickListener {
            GlobalScope.launch {
                startActivity(Intent(this@ProfileActivity, MainActivity::class.java))
            }
            val resolver = this.applicationContext.contentResolver
            val picture = BitmapFactory.decodeStream(
                resolver.openInputStream(Uri.parse(imageUri.toString())))
            writeBitmapToFile(this,picture)

        }
        val image = BitmapFactory.decodeFile(this.applicationContext.filesDir.path + File.separator +"dataFoto"+ File.separator +"fotoProfile.png")
        binding.ivSetImage.setImageBitmap(image)
    }

    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
    // buat variable baru untuk POST date.mont,year ke API (format ini sudah dalam bentuk string)
        binding.tvTgllahir.text= sdf.format(cal.getTime())
    }

    private fun writeBitmapToFile(applicationContext: Context, bitmap: Bitmap): Uri {
        val name = "fotoProfile.png"
        val outputDir = File(applicationContext.filesDir, "dataFoto")
        if (!outputDir.exists()) {
            outputDir.mkdirs() // should succeed
        }
        val outputFile = File(outputDir, name)
        var out: FileOutputStream? = null
        try {
            out = FileOutputStream(outputFile)
            bitmap.compress(Bitmap.CompressFormat.PNG, 0 /* ignored for PNG */, out)
        } finally {
            out?.let {
                try {
                    it.close()
                } catch (ignore: IOException) {
                }

            }
        }
        return Uri.fromFile(outputFile)
    }
    private fun checkingPermissions() {
        if (isGranted(
                this,
                Manifest.permission.CAMERA,
                arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                REQUEST_CODE_PERMISSION,
            )
        ) {
            chooseImageDialog()
        }
    }
    private fun isGranted(
        activity: Activity,
        permission: String,
        permissions: Array<String>,
        request: Int,
    ): Boolean {
        val permissionCheck = ActivityCompat.checkSelfPermission(activity, permission)
        return if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                showPermissionDeniedDialog()
            } else {
                ActivityCompat.requestPermissions(activity, permissions, request)
            }
            false
        } else {
            true
        }
    }
    private fun showPermissionDeniedDialog() {
        AlertDialog.Builder(this)
            .setTitle("Permission Denied")
            .setMessage("Permission is denied, Please allow permissions from App Settings.")
            .setPositiveButton(
                "App Settings"
            ) { _, _ ->
                val intent = Intent()
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                val uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
            .show()
    }
    private fun chooseImageDialog() {
        AlertDialog.Builder(this)
            .setMessage("Pilih Gambar")
            .setPositiveButton("Gallery") { _, _ -> openGallery() }
            .show()
    }
    private fun openGallery() {
        intent.type = "image/*"
        galleryResult.launch("image/*")
    }
    private val galleryResult =
        registerForActivityResult(ActivityResultContracts.GetContent()) { result ->
            imageUri = result
            binding.ivSetImage.setImageURI(result)
        }

}