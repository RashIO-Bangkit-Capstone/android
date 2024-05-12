package id.rashio.android.ui.screen.detection

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetectionViewModel @Inject constructor() : ViewModel() {

    var imageUri by mutableStateOf<String?>(null)
        private set

    fun selectImage(uri: Uri?) {
        viewModelScope.launch {
            imageUri = uri.toString()
        }
    }


}