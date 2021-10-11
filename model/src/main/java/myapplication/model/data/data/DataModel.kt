package myapplication.model.data.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataModel(
    val text: String = "",
    val meanings: List<Meaning> = listOf()
): Parcelable
