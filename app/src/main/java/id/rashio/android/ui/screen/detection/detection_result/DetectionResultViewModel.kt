package id.rashio.android.ui.screen.detection.detection_result

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.rashio.android.data.repository.DiseaseRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetectionResultViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val diseaseRepository: DiseaseRepository
) : ViewModel() {


    private val detectionResult: String = checkNotNull(savedStateHandle["detectionResult"])
    private val detectionPercentage: String = checkNotNull(savedStateHandle["detectionPercentage"])
    private val detectionImage: String = checkNotNull(savedStateHandle["detectionImage"])

    val uiState: StateFlow<DiseaseUiState> = flow {
        emit(diseaseRepository.getDisease(detectionResult))
    }.map {
        it.fold(
            onSuccess = { diseaseResponse ->
                DiseaseUiState.Success(
                    diseaseName = diseaseResponse.data.name,
                    diseaseDescription = diseaseResponse.data.descriptions.joinToString("\n"),
                    diseaseTreatment = diseaseResponse.data.treatments.joinToString("\n"),
                    percentage = detectionPercentage.replace("$", "").toFloat(),
                    imageUrl = detectionImage
                )
            },
            onFailure = { error ->
                DiseaseUiState.Error(error.message ?: "Error")
            }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
        initialValue = DiseaseUiState.Loading
    )

}

sealed interface DiseaseUiState {
    object Loading : DiseaseUiState
    data class Success(
        val diseaseName: String,
        val diseaseDescription: String,
        val diseaseTreatment: String,
        val percentage: Float,
        val imageUrl: String
    ) : DiseaseUiState

    data class Error(val message: String) : DiseaseUiState
}