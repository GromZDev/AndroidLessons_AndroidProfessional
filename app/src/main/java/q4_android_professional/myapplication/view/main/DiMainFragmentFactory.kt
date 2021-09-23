package q4_android_professional.myapplication.view.main

import dagger.assisted.AssistedFactory
import q4_android_professional.myapplication.model.DataModel

@AssistedFactory
interface DiMainFragmentFactory {

    fun create(
        data: List<DataModel>?,
        onListItemClickListener: MainAdapter.OnListItemClickListener
    ): MainAdapter
}