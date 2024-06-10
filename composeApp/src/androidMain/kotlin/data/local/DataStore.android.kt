package data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

/**
 * @Author: Abdul Rehman
 */

actual fun createDataStore(context: Any?): DataStore<Preferences> {
    require(
        value = context is Context,
        lazyMessage = { "Context object is required." }
    )
    return AppSetting.getDataStore(
        producePath = {
            context.filesDir
                .resolve(dataStoreFileName)
                .absolutePath
        }
    )
}