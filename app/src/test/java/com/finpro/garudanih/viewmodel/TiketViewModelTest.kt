package com.finpro.garudanih.viewmodel

import com.finpro.garudanih.model.ResponseListTiket
import com.finpro.garudanih.network.ApiInterface
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import retrofit2.Call

class TiketViewModelTest {
    lateinit var test : ApiInterface
    @Before
    fun setUp(){
        test = mockk()
    }

    @Test
    fun getLdListTiket(): Unit = runBlocking {
        val getlisttiket = mockk<Call<ResponseListTiket>>()

        every {
            runBlocking {
                test.getAllListTicket()
            }
        } returns getlisttiket

        val result =test.getAllListTicket()

        verify {
            runBlocking { test.getAllListTicket() }
        }
        assertEquals(result, getlisttiket)
    }


    @Test
    fun getlistTiketInter(): Unit = runBlocking {
        val getlistinter = mockk<Call<ResponseListTiket>>()

        every {
            runBlocking {
                test.getAllListTicketIntr()
            }
        } returns getlistinter

        val result =test.getAllListTicketIntr()

        verify {
            runBlocking { test.getAllListTicketIntr() }
        }
        assertEquals(result, getlistinter)
    }
}