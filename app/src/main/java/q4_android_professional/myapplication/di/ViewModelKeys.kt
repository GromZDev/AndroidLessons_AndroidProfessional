package q4_android_professional.myapplication.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
Класс с ключами для хранения ViewModel в фабрике
 */

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)

@MapKey
annotation class ViewModelKeys(val value: KClass<out ViewModel>)
