package myapplication.model.data.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class SearchResultDTO(
    @field:SerializedName("text") val text: String?,
    @field:SerializedName("meanings") val meanings: List<MeaningsDTO>?
) : Parcelable
