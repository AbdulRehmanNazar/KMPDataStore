package data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map


/**
 * @Author: Abdul Rehman
 */
public class DataStoreRepository(private val dataStore: DataStore<Preferences>) {
    companion object {
        val TIMESTAMP_KEY = longPreferencesKey(name = "saved_timestamp")

    }

    suspend fun saveTimeStamp(timeStamp: Long): Boolean = try {
        dataStore.edit { preferences ->
            preferences.set(key = TIMESTAMP_KEY, value = timeStamp)
        }
        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }

    fun readTimeStamp(): Flow<Long> =
        dataStore.data
            .catch { emptyFlow<Long>() }
            .map { prefrences ->
                prefrences[TIMESTAMP_KEY] ?: 0L

            }
}