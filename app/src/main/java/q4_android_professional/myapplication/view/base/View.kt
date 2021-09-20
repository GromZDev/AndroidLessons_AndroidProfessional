package q4_android_professional.myapplication.view.base

import q4_android_professional.myapplication.model.AppState

interface View {
    /** View имеет только один метод. В него приходит некое
     * состояние приложения */

    fun renderData(appState: AppState)
}