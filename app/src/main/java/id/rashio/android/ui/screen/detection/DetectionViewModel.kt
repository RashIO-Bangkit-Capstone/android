package id.rashio.android.ui.screen.detection

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.rashio.android.data.repository.PredictionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class DetectionViewModel @Inject constructor(
    private val predictionRepository: PredictionRepository
) : ViewModel() {

    var imageDetection by mutableStateOf<File?>(null)

    var uiState = MutableStateFlow<DetectionUiState>(DetectionUiState.Empty)
        private set

    fun predictImage() {
        viewModelScope.launch {
            uiState.value = DetectionUiState.Loading
            imageDetection?.let { it ->
                val compressedImage = withContext(Dispatchers.IO) {
                    reduceFileImage(it)
                }
                val predictionResult = predictionRepository.predictImage(compressedImage)
                predictionResult.fold(
                    onSuccess = {
                        uiState.value = DetectionUiState.Success(
                            it.data.result,
                            it.data.percentage,
                            it.data.imageUrl
                        )
                    },
                    onFailure = {
                        uiState.value = DetectionUiState.Error(it.message ?: "Error")
                    }
                )
            }
        }
    }

    private val MAXIMAL_SIZE = 1000000

    private fun reduceFileImage(file: File): File {
        val bitmap = BitmapFactory.decodeFile(file.path)
        var compressQuality = 100
        var streamLength: Int
        do {
            val bmpStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, compressQuality, bmpStream)
            val bmpPicByteArray = bmpStream.toByteArray()
            streamLength = bmpPicByteArray.size
            compressQuality -= 5
        } while (streamLength > MAXIMAL_SIZE)
        bitmap.compress(Bitmap.CompressFormat.JPEG, compressQuality, FileOutputStream(file))
        return file
    }

}

sealed interface DetectionUiState {
    object Loading : DetectionUiState
    object Empty : DetectionUiState
    data class Success(
        val nameDisease: String,
        val percentage: Float,
        val imageUrl: String
    ) : DetectionUiState

    data class Error(val message: String) : DetectionUiState
}