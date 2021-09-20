package q4_android_professional.myapplication.model

/** Это наши состояния приложения. Их у нас 3 шт*/
sealed class AppState {

    data class Success(val data: List<DataModel>?) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()

}