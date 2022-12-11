package com.finpro.garudanih.viewmodel

import androidx.lifecycle.ViewModel
import com.finpro.garudanih.repository.DataUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repo: DataUserRepository): ViewModel() {
    fun currentUser(token : String) = repo.getDataUser(token)
    fun getCurrentObserve() = repo.getCurrentUserObserve()

    fun orderTiketPesawat(token: String,
                          ticketId:Int,
                          orderBy : String,
                          ktp: String,
                          numChair : Int) = repo.postOrderTiket(token,ticketId,orderBy,ktp,numChair)
    fun orderTiketObserve() = repo.postOrderObserve()

    fun historyUser(token: String)= repo.getHistory(token)
    fun getHistoryObserve() = repo.getHistoryObserve()

    fun paidUser(transId: Int) = repo.callUpdatePaid(transId)
    fun paidUserObserve() = repo.putPaidUserObserve()
}