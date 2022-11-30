package com.finpro.garudanih.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.finpro.garudanih.datastore.UserLoginPreferences
import com.finpro.garudanih.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(@ApplicationContext context : Context, private val repository : AuthRepository):ViewModel() {
    private val userPreferences = UserLoginPreferences(context)

    fun setToken(token : String){
        viewModelScope.launch {
            userPreferences.setToken(token)
        }
    }
    fun deleteToken(){
        viewModelScope.launch {
            userPreferences.deleteToken()
        }
    }
    fun getToken(): LiveData<String> = userPreferences.getToken().asLiveData()

    fun doLogin(email :String,password : String) = repository.doSignIn(email,password)
    fun ldSigIn() = repository.ldSignIn()
}