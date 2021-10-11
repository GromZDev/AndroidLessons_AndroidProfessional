package myapplication.repository

import myapplication.model.data.AppState
import myapplication.model.data.data.DataModel
import myapplication.model.data.data.Meaning
import myapplication.model.data.data.TranslatedMeaning
import myapplication.model.data.dto.SearchResultDTO
import myapplication.repository.room.HistoryEntity

fun mapSearchResultToResult(searchResults: List<SearchResultDTO>): List<DataModel> {
    return searchResults.map { searchResult ->
        var meanings: List<Meaning> = listOf()
        searchResult.meanings?.let {

            meanings = it.map { meaningsDto ->
                Meaning(
                    TranslatedMeaning(meaningsDto.translation?.translation ?: ""),
                    meaningsDto.previewUrl ?: "",
                    meaningsDto.imageUrl ?: "",
                    meaningsDto.transcription ?: ""
                )
            }
        }
        DataModel(searchResult.text ?: "", meanings)
    }
}


fun parseOnlineSearchResults(appState: AppState): AppState {
    return AppState.Success(mapResult(appState, true))
}

fun convertMeaningsToString(meanings: List<Meaning>): String {
    var meaningsSeparatedByComma = String()
    for ((index, meaning) in meanings.withIndex()) {
        meaningsSeparatedByComma += if (index + 1 != meanings.size) {
            String.format("%s%s", meaning.translatedMeaning.translatedMeaning, ", ")
        } else {
            meaning.translatedMeaning.translatedMeaning
        }
    }
    return meaningsSeparatedByComma
}

fun convertNoteToString(meanings: List<Meaning>): String {
    var meaningsSeparatedByComma = String()
    for ((index, meaning) in meanings.withIndex()) {
        meaningsSeparatedByComma += if (index + 1 != meanings.size) {
            if (meaning.translatedMeaning.noteNew.isEmpty()) {
                String.format("")
            } else {
                String.format("%s%s", meaning.translatedMeaning.noteNew, ", ")
            }
        } else {
            meaning.translatedMeaning.noteNew
        }
    }
    return meaningsSeparatedByComma
}

/** Room +++++++++++++++++++++++++++++++++++++++++++++*/
fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<SearchResultDTO> {
    val searchResult = ArrayList<SearchResultDTO>()
    if (!list.isNullOrEmpty()) {
        for (entity in list) {
            searchResult.add(SearchResultDTO(entity.word, null))
        }
    }
    return searchResult
}

fun convertDataModelSuccessToEntity(appState: AppState): HistoryEntity? {
    return when (appState) {
        is AppState.Success -> {
            val searchResult = appState.data
            if (searchResult.isNullOrEmpty() || searchResult[0].text.isEmpty()) {
                null
            } else {
                HistoryEntity(
                    word = searchResult[0].text,
                    description = searchResult[0].meanings[0].translatedMeaning.translatedMeaning
//                    searchResult[0].meanings?.get(0)?.translation?.note,
//                    searchResult[0].meanings?.get(0)?.transcription,
//                    searchResult[0].meanings?.get(0)?.translation?.note
                )
            }
        }
        else -> null
    }
}

fun parseLocalSearchResults(appState: AppState): AppState {
    return AppState.Success(mapResult(appState, false))
}

private fun mapResult(
    appState: AppState,
    isOnline: Boolean
): List<DataModel> {
    val newSearchResults = arrayListOf<DataModel>()
    when (appState) {
        is AppState.Success -> {
            getSuccessResultData(appState, isOnline, newSearchResults)
        }
    }
    return newSearchResults
}

private fun getSuccessResultData(
    appState: AppState.Success,
    isOnline: Boolean,
    newDataModels: ArrayList<DataModel>
) {
    val dataModels: List<DataModel> = appState.data as List<DataModel>
    if (dataModels.isNotEmpty()) {
        if (isOnline) {
            for (searchResult in dataModels) {
                parseOnlineResult(searchResult, newDataModels)
            }
        } else {
            for (searchResult in dataModels) {
                newDataModels.add(DataModel(searchResult.text, arrayListOf()))
            }
        }
    }
}

private fun parseOnlineResult(dataModel: DataModel, newDataModels: ArrayList<DataModel>) {
    if (dataModel.text.isNotBlank() && dataModel.meanings.isNotEmpty()) {
        val newMeanings = arrayListOf<Meaning>()
        newMeanings.addAll(dataModel.meanings.filter {
            it.translatedMeaning.translatedMeaning.isNotBlank()
        })
        if (newMeanings.isNotEmpty()) {
            newDataModels.add(DataModel(dataModel.text, newMeanings))
        }
    }

}
/** ++++++++++++++++++++++++++++++++++++++++++++++++ */