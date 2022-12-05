package com.finpro.garudanih.viewmodel

import androidx.lifecycle.ViewModel
import com.finpro.garudanih.repository.DataUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repo: DataUserRepository): ViewModel() {
    fun currentUser(token : String) = repo.getDataUser(token)
    fun getCurrentObserve() = repo.getCurrentUserObserve()

    fun updateUser(token :String,
                   nomor : String,
                   tanggallahir :String,
                   kota : String) = repo.updateCurrentUser(token,nomor,tanggallahir,kota)

    fun getUpdateUserObserver() = repo.updateCurrentUserObserve()

}