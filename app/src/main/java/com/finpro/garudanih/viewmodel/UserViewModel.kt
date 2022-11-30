package com.finpro.garudanih.viewmodel

import androidx.lifecycle.ViewModel
import com.finpro.garudanih.repository.DataUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repo: DataUserRepository): ViewModel() {
    fun currentUser(token : String) = repo.getDataUser(token)
    fun getCurrentObserve() = repo.getCurrentUserObserve()
}