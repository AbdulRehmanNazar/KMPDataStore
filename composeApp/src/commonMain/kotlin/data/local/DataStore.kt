package data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences


/**
 * @Author: Abdul Rehman
 */


expect fun createDataStore(context: Any? = null): DataStore<Preferences>