package myapplication.model.data.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meaning(
    val translatedMeaning: TranslatedMeaning = TranslatedMeaning(),
    val previewUrlNew: String = "",
    val imageUrlNew: String = "",
    val transcriptionNew: String = ""
): Parcelable
