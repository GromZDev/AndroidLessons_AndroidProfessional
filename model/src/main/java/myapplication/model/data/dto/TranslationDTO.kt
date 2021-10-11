package myapplication.model.data.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class TranslationDTO(
   @field:SerializedName("text") val translation: String?,
   @field:SerializedName("note") val note: String?
): Parcelable
