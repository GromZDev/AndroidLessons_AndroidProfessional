package q4_android_professional.myapplication.di

import com.google.android.material.imageview.ShapeableImageView
import dagger.Module
import dagger.Provides
import q4_android_professional.myapplication.utils.GlideImageLoader
import q4_android_professional.myapplication.utils.ImageLoader

@Module
class ImageLoaderModule {

    @Provides
    fun imageLoader(): ImageLoader<ShapeableImageView> =
        GlideImageLoader()

}