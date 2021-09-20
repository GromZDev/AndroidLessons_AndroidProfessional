package q4_android_professional.myapplication

interface ImageLoader<T> {

    fun loadInto(url: String, container: T)

}