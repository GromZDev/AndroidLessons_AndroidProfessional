package q4_android_professional.myapplication.model.datasource.roomlocal

import myapplication.model.data.AppState
import q4_android_professional.myapplication.model.datasource.retrofitremote.DataSource

interface DataSourceLocal<T> : DataSource<T> {
    suspend fun saveToDB(appState: AppState)
}
