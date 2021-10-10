package myapplication.repository.repolocal

import myapplication.model.data.AppState
import myapplication.repository.reporemote.Repository

interface RepositoryLocal<T> : Repository<T> {
    suspend fun saveToDB(appState: AppState)
}