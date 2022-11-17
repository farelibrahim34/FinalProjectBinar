package com.finpro.garudanih.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "datauser")
class DataStoreLogin(private val context: Context){

    private val NamaPanjang = stringPreferencesKey("NamaPanjang")
    private val Username = stringPreferencesKey("Username")
    private val Email = stringPreferencesKey("Email")
    private val NoHp = stringPreferencesKey("NoHp")
    private val TglLahir = stringPreferencesKey("TglLahir")
    private val Alamat = stringPreferencesKey("Alamat")
    private val PW = stringPreferencesKey("password")
    private val PWU = stringPreferencesKey("passwordulang")

    suspend fun saveData(
        namapanjang: String,
        username : String,
        email: String,
        nohp : String,
        tgllahir:String,
        alamat: String,
        pw:String,
        pwu:String ){

       context.dataStore.edit {
           it[NamaPanjang] = namapanjang
            it[Username] = username
            it[Email] = email
            it[NoHp] = nohp
            it[TglLahir] = tgllahir
            it[Alamat] = alamat
            it[PW] = pw
            it[PWU] = pwu
        }    }
    suspend fun clearData(){
        context.dataStore.edit { it.clear() } }

    val userNamaPanjang: Flow<String> = context.dataStore.data.map { it[NamaPanjang] ?: ""}
    val userName: Flow<String> = context.dataStore.data.map { it[Username] ?: ""}
    val userEmail: Flow<String> = context.dataStore.data.map { it[Email] ?: "" }
    val userNoHp: Flow<String> = context.dataStore.data.map { it[NoHp] ?: "" }
    val userTglLahir: Flow<String> = context.dataStore.data.map { it[TglLahir] ?: "" }
    val userAlamat: Flow<String> = context.dataStore.data.map { it[Alamat] ?: "" }
    val userPw: Flow<String> = context.dataStore.data.map { it[PW] ?: "" }
    val userPWU: Flow<String> = context.dataStore.data.map { it[PWU] ?: "" }}