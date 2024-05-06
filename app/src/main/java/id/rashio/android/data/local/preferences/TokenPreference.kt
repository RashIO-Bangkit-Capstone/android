package id.rashio.android.data.local.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

val Context.tokenDataStore: DataStore<Preferences> by preferencesDataStore(name = "token")

class TokenPreference private constructor(private val dataStore: DataStore<Preferences>) {

    private val TOKEN_KEY = stringPreferencesKey("token")
    private val NAME_KEY = stringPreferencesKey("name")
    private val EMAIL_KEY = stringPreferencesKey("email")
    private val PHONE_KEY = stringPreferencesKey("phone")
    private val ID_KEY = stringPreferencesKey("id")

    val userData = dataStore.data
        .map { preferences ->
            UserData(
                name = preferences[NAME_KEY] ?: "",
                email = preferences[EMAIL_KEY] ?: "",
                phoneNumber = preferences[PHONE_KEY] ?: "",
                token = preferences[TOKEN_KEY] ?: "",
                id = preferences[ID_KEY] ?: ""
            )
        }



    suspend fun saveUserData(name: String, email: String, phoneNumber: String, token: String, id: String){
        dataStore.edit { preferences ->
            preferences[NAME_KEY] = name
            preferences[EMAIL_KEY] = email
            preferences[PHONE_KEY] = phoneNumber
            preferences[TOKEN_KEY] = token
            preferences[ID_KEY] = id
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: TokenPreference? = null

        fun getInstance(dataStore: DataStore<Preferences>): TokenPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = TokenPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}

data class UserData (
    val name: String,
    val email: String,
    val phoneNumber: String,
    val token: String,
    val id: String
)