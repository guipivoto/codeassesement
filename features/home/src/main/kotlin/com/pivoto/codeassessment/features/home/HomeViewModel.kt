package com.pivoto.codeassessment.features.home

import androidx.lifecycle.viewModelScope
import com.pivoto.codeassessment.features.home.models.ListItemModel
import com.pivoto.codeassessment.features.navigation.destinations.DetailsScreenDestination
import com.pivoto.codeassessment.features.navigation.results.DetailsScreenResult
import com.pivoto.codeassessment.models.Photo
import com.pivoto.codeassessment.navigation.NavigationResult
import com.pivoto.codeassessment.repository.PhotosRepository
import com.pivoto.codeassessment.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val photosRepository: PhotosRepository
): BaseViewModel() {

    private var remotePhotoList: List<Photo> = emptyList()

    private val _listItems = MutableStateFlow<List<ListItemModel>>(emptyList())

    /**
     * List of items that should be displayed by the fragment
     */
    val listItems: StateFlow<List<ListItemModel>> = _listItems

    private val _photoTitle = MutableStateFlow<String?>(null)

    /**
     * Title of last photo visualized
     */
    val photoTitle: StateFlow<String?> = _photoTitle

    init {
        viewModelScope.launch {
            photosRepository.getPhotos().onSuccess { photos ->
                remotePhotoList = photos
                _listItems.value = photos.map {
                    ListItemModel(
                        it.id,
                        it.title,
                        "https://farm${it.farm}.staticflickr.com/${it.server}/${it.id}_${it.secret}_t.jpg"
                    )
                }
            }
         }
    }

    /**
     * Method that can be invoked when an item is clicked in the home screen
     * @param itemModel [ListItemModel] clicked
     */
    fun handleItemClicked(itemModel: ListItemModel) {
        remotePhotoList.firstOrNull {
            it.id == itemModel.id
        }?.let {
            navigateForResult(DetailsScreenDestination(it))
        }
    }

    override fun onNavigationResult(result: NavigationResult) {
        if (result is DetailsScreenResult) {
            _photoTitle.value = result.getArgsBundle().getString(DetailsScreenResult.PHOTO_TITLE_PARAM)
        }
    }
}
