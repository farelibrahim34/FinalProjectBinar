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
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.finpro.garudanih.MainActivity
import com.finpro.garudanih.databinding.ActivityProfileBinding
import com.finpro.garudanih.view.HomeBottomActivity
import com.finpro.garudanih.view.auth.LoginActivity
import com.finpro.garudanih.viewmodel.AuthViewModel
import com.finpro.garudanih.viewmodel.UserViewModel
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
    private lateinit var authViewModel: AuthViewModel
    private lateinit var userViewModel : UserViewModel

    private val REQUEST_CODE_PERMISSION = 100
    private var imageUri: Uri? = Uri.EMPTY

    var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getProfile()
        setGetDataUser()

        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
//        logout
        logout()

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

    private fun logout(){
        binding.btnLogout.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java).also {
                authViewModel.apply {
                    deleteToken()
                    deleteData()
                }
            })
            Toast.makeText(this,"Berhasil Logout", Toast.LENGTH_SHORT).show()
        }
    }
    private fun getProfile(){
        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        authViewModel.getToken().observe(this){
            if (it != null){
                userViewModel.currentUser("Bearer $it")
            }else{
                Log.d("TOKEN","Token Null")
            }
        }
    }

    private fun setGetDataUser(){
        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.getCurrentObserve().observe(this){
            if (it != null){
                binding.tvNamauser.setText(it.name)
                binding.tvEmailuser.setText(it.email)
                binding.etPhone.setText(it.phone)
                binding.tvTgllahir.setText(it.birth)
            }else{
                Log.d("PROFILE","Profile Null")
            }
        }

        authViewModel.getUser().observe(this){
            if (it != null){
                binding.tvNamauser.setText(it)
            }
        }
        authViewModel.getEmail().observe(this){
            if (it != null){
                binding.tvEmailuser.setText(it)
            }
        }
        authViewModel.getNoHp().observe(this){
            if (it != null){
                binding.etPhone.setText(it)
            }
        }
        authViewModel.getTglLahir().observe(this){
            if (it != null){
                binding.tvTgllahir.setText(it)
            }
        }
        authViewModel.getImage().observe(this){
            if (it != null && it != "undefined"){
                Log.d("PHOTO_URL",it)
                binding.apply {
                    Glide.with(root.context).load(it).into(ivSetImage)
                }
            }
        }

    }
}