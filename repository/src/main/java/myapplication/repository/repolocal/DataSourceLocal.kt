package myapplication.repository.repolocal

import myapplication.model.data.AppState
import myapplication.repository.reporemote.DataSource

interface DataSourceLocal<T> : DataSource<T> {
    suspend fun saveToDB(appState: AppState)
}
