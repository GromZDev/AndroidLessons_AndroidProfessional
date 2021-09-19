package q4_android_professional.myapplication.model

import com.google.gson.annotations.SerializedName

class Meanings (
    @field:SerializedName("translation") val translation: Translation?,
    @field:SerializedName("imageUrl") val imageUrl: String?,
    @field:SerializedName("transcription") val transcription: String?
)