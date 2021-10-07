package q4_android_professional.myapplication.utils

import q4_android_professional.myapplication.model.AppState

class StopwatchStateCalculator(
    private val timestampProvider: TimestampProvider,
    private val elapsedTimeCalculator: ElapsedTimeCalculator,
) {

    fun calculateRunningState(oldState: AppState): AppState.Running =
        when (oldState) {
            is AppState.Running -> oldState
            is AppState.Paused -> {
                AppState.Running(
                    startTime = timestampProvider.getMilliseconds(),
                    elapsedTime = oldState.elapsedTime
                )
            }
        }

    fun calculatePausedState(oldState: AppState): AppState.Paused =
        when (oldState) {
            is AppState.Running -> {
                val elapsedTime = elapsedTimeCalculator.calculate(oldState)
                AppState.Paused(elapsedTime = elapsedTime)
            }
            is AppState.Paused -> oldState
        }
}