package id.rashio.android.ui.screen.profile.detection_history

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.rashio.android.data.repository.PredictionRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetectionHistoryViewModel @Inject constructor(
    predictionRepository: PredictionRepository,
) : ViewModel() {


    val historyDetection = predictionRepository.getHistory()
        .onEach {
            Log.d("History", "$it")
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
            initialValue = emptyList()
        )
}