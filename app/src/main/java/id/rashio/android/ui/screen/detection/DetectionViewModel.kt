package id.rashio.android.ui.screen.detection

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.rashio.android.data.repository.PredictionRepository
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class DetectionViewModel @Inject constructor(
    private val predictionRepository: PredictionRepository
) : ViewModel() {


    var imageDetection by mutableStateOf<File?>(null)

    fun predictImage() {
        viewModelScope.launch {
            imageDetection?.let {
                predictionRepository.predictImage(it)
            }
        }
    }


}