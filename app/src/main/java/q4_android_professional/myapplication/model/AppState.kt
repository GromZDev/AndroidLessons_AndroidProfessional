package q4_android_professional.myapplication.model

/** Это наши состояния приложения. Их у нас 2 шт*/
sealed class AppState {

    data class Paused(val elapsedTime: Long) : AppState()
    data class Running(val startTime: Long, val elapsedTime: Long) : AppState()

}
