package myapplication.model.data.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TranslatedMeaning(
    val translatedMeaning: String = "",
    val noteNew: String = ""
): Parcelable