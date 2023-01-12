@file:Suppress("HasPlatformType", "UsePropertyAccessSyntax")

package com.finpro.garudanih.view.profile

import android.app.DatePickerDialog
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.finpro.garudanih.MainActivity
import com.finpro.garudanih.databinding.ActivityProfileBinding
import com.finpro.garudanih.utils.UpdateProfile
import com.finpro.garudanih.view.HomeBottomActivity
import com.finpro.garudanih.viewmodel.AuthViewModel
import com.finpro.garudanih.viewmodel.UserViewModel
import com.finpro.garudanih.viewmodel.ViewModelUser
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@Suppress("UNNECESSARY_NOT_NULL_ASSERTION", "UsePropertyAccessSyntax",
    "MemberVisibilityCanBePrivate", "ReplaceGetOrSet", "ObjectLiteralToLambda",
    "ConvertToStringTemplate", "PrivatePropertyName", "unused"
)
@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProfileBinding
    private var imageMultiPart: MultipartBody.Part? = null
    private var imageFile: File? = null
    private lateinit var authViewModel: AuthViewModel
    private var tokenHistory : String = ""
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
            openGallery()
        }
        binding.btnSimpan.setOnClickListener {
            GlobalScope.launch {
                startActivity(Intent(this@ProfileActivity, MainActivity::class.java))
            }

        }
        binding.tvPrivacyPolicy.setOnClickListener {
            val url = "https://www.privacypolicyonline.com/live.php?token=LAdIxN9mK04Y9nItIY7JWXheDzZ2wI5w"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
        setEditProfile()
    }

    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        // buat variable baru untuk POST date.mont,year ke API (format ini sudah dalam bentuk string)
        binding.tvTgllahir.text= sdf.format(cal.getTime())
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
                binding.tvAlamat.setText(it.city)
                val url = it.image
                Glide.with(this).load(url).circleCrop().into(binding.ivSetImage)
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
        authViewModel.getKota().observe(this){
            if (it != null){
                binding.tvAlamat.setText(it)
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

    private fun doUpdateProfile(){
        binding.btnSimpan.setOnClickListener {
            val nomor = binding.etPhone.text.toString()
            val ttl = binding.tvTgllahir.text.toString().trim()
            val kota = binding.tvAlamat.text.toString()
            val validate = UpdateProfile.validateEditProfile(nomor,ttl,kota)
            if (validate == "success"){
                authViewModel.getToken().observe(this){token->
                    if (token != null){
                        if (nomor.isNotBlank() && ttl.isNotBlank() && kota.isNotBlank()){
                            userViewModel.updateUser("Bearer $token", nomor, ttl,kota)
                            userViewModel.getUpdateUserObserver().observe(this){
                                if (it != null){
                                    Toast.makeText(this, "Update Profile Success", Toast.LENGTH_SHORT).show()
                                }else{
                                    Toast.makeText(this, "Failed Update Profile", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }else{
                            Toast.makeText(this, "Cannot be empty", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Log.d("TOKEN","Token Null")
                    }
                }
            }
        }
    }
    private fun setEditProfile(){
        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        authViewModel.getToken().observe(this){token->
            if (token != null){
                tokenHistory = "Bearer "+token
            }
        }
        binding.btnSimpan.setOnClickListener {
            val name = binding.tvNamauser.text.toString().toRequestBody("multipart/form-data".toMediaType())
            val nomor = binding.etPhone.text.toString().toRequestBody("multipart/form-data".toMediaType())
            val ttl = binding.tvTgllahir.text.toString().toRequestBody("multipart/form-data".toMediaType())
            val kota = binding.tvAlamat.text.toString().toRequestBody("multipart/form-data".toMediaType())

            val viewModelEditProfile = ViewModelProvider(this).get(ViewModelUser::class.java)
            viewModelEditProfile.editProfileUser.observe(this){
                if (it != null){
                    Toast.makeText(this, "Berhasil Update Profile", Toast.LENGTH_SHORT).show()
                }
            }
            viewModelEditProfile.callEditProfile(tokenHistory,name,imageMultiPart!!,nomor,ttl,kota)
        }
    }
    fun openGallery(){
        getContent.launch("image/*")
    }
    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                val contentResolver: ContentResolver = this!!.contentResolver
                val type = contentResolver.getType(it)
                imageUri = it

                val fileNameimg = "${System.currentTimeMillis()}.png"
                binding.ivSetImage.setImageURI(it)
                Toast.makeText(this, "$imageUri", Toast.LENGTH_SHORT).show()

                val tempFile = File.createTempFile("and1-", fileNameimg, null)
                imageFile = tempFile
                val inputstream = contentResolver.openInputStream(uri)
                tempFile.outputStream().use    { result ->
                    inputstream?.copyTo(result)
                }
                val requestBody: RequestBody = tempFile.asRequestBody(type?.toMediaType())
                imageMultiPart = MultipartBody.Part.createFormData("image", tempFile.name, requestBody)
            }
        }

}