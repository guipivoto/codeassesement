package com.pivoto.codeassessment.features.details

import androidx.lifecycle.SavedStateHandle
import com.pivoto.codeassessment.features.details.models.PhotoDetailsModel
import com.pivoto.codeassessment.features.navigation.destinations.DetailsScreenDestination
import com.pivoto.codeassessment.features.navigation.results.DetailsScreenResult
import com.pivoto.codeassessment.models.Photo
import com.pivoto.codeassessment.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val photo: Photo = savedStateHandle.get<Photo>(DetailsScreenDestination.PHOTO_ID_PARAM)!!

    private val _photoDetails: MutableStateFlow<PhotoDetailsModel> by lazy {
        MutableStateFlow(
            PhotoDetailsModel(
                "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg"
            )
        )
    }
    val photoDetails: StateFlow<PhotoDetailsModel> = _photoDetails

    init {
        setResult(DetailsScreenResult(photo.title))
    }
}
