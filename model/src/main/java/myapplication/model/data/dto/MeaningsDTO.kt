package myapplication.model.data.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class MeaningsDTO(
    @field:SerializedName("translation") val translation: TranslationDTO?,
    @field:SerializedName("previewUrl") val previewUrl: String?,
    @field:SerializedName("imageUrl") val imageUrl: String?,
    @field:SerializedName("transcription") val transcription: String?
) : Parcelable