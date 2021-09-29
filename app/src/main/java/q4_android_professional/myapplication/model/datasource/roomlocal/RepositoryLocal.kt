package q4_android_professional.myapplication.model.datasource.roomlocal

import q4_android_professional.myapplication.model.AppState
import q4_android_professional.myapplication.repository.Repository

interface RepositoryLocal<T> : Repository<T> {
    suspend fun saveToDB(appState: AppState)
}