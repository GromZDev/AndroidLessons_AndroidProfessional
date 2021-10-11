package myapplication.historyscreen

import myapplication.model.data.AppState
import myapplication.model.data.data.DataModel
import myapplication.model.data.data.Meaning
import myapplication.model.data.dto.MeaningsDTO
import myapplication.model.data.dto.SearchResultDTO
import myapplication.repository.room.HistoryEntity


fun parseOnlineSearchResults(appState: AppState): AppState {
    return AppState.Success(mapResult(appState, true))
}

fun convertMeaningsToString(meanings: List<MeaningsDTO>): String {
    var meaningsSeparatedByComma = String()
    for ((index, meaning) in meanings.withIndex()) {
        meaningsSeparatedByComma += if (index + 1 != meanings.size) {
            String.format("%s%s", meaning.translation?.translation, ", ")
        } else {
            meaning.translation?.translation
        }
    }
    return meaningsSeparatedByComma
}

fun convertNoteToString(meanings: List<MeaningsDTO>): String {
    var meaningsSeparatedByComma = String()
    for ((index, meaning) in meanings.withIndex()) {
        meaningsSeparatedByComma += if (index + 1 != meanings.size) {
            if (meaning.translation?.note.isNullOrEmpty()) {
                String.format("")
            } else {
                String.format("%s%s", meaning.translation?.note, ", ")
            }
        } else {
            meaning.translation?.note
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
            if (searchResult.isNullOrEmpty() || searchResult[0].text.isNullOrEmpty()) {
                null
            } else {
                HistoryEntity(
                    word = searchResult[0].text,
                    description = searchResult[0].meanings[0].transcriptionNew
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
        for (meaning in dataModel.meanings) {
            if (meaning.translatedMeaning.translatedMeaning.isBlank()) {
                newMeanings.add(
                    Meaning(
                        meaning.translatedMeaning,
                        meaning.previewUrlNew,
                        meaning.imageUrlNew,
                        meaning.transcriptionNew
                    )
                )
            }
        }
        if (newMeanings.isNotEmpty()) {
            newDataModels.add(DataModel(dataModel.text, newMeanings))
        }
    }
}
/** ++++++++++++++++++++++++++++++++++++++++++++++++ */