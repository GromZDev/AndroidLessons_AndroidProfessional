package q4_android_professional.myapplication

interface View {
    /** View имеет только один метод. В него приходит некое
     * состояние приложения */

    fun renderData(appState: AppState)
}