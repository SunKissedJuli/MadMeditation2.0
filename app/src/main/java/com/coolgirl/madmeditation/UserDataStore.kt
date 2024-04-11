package com.coolgirl.madmeditation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.preferencesKey
import androidx.datastore.preferences.toMutablePreferences
import com.coolgirl.madmeditation.Models.UserLoginData
import com.coolgirl.madmeditation.Models.UserLoginDataResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private var userDataStore : DataStore<Preferences>? = null
var coroutineScope : CoroutineScope? = null


@Composable
fun InitDataStore(){
    userDataStore = LocalContext.current.createDataStore(name = "user_data_store")
    coroutineScope = rememberCoroutineScope()
}
@Composable
fun GetDataStore() : DataStore<Preferences>? {
    return userDataStore
}

fun SetLoginData(newUser: UserLoginData) {
    coroutineScope?.launch(Dispatchers.IO) {
        userDataStore?.updateData { prefs ->
            prefs.toMutablePreferences().apply {
                set(UserScheme.PASSWORD, newUser.password.toString())
                set(UserScheme.EMAIL, newUser.email.toString())
                set(UserScheme.IS_AUTORIZE,true)
            }
        }
    }
}

object UserScheme {
    val EMAIL = preferencesKey<String>("email")
    val PASSWORD = preferencesKey<String>("password")
    val IS_AUTORIZE = preferencesKey<Boolean>("is_autorize")
}