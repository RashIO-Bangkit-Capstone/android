package id.rashio.android.data.repository

import androidx.lifecycle.ViewModel
import id.rashio.android.data.local.preferences.TokenPreference
import id.rashio.android.data.model.HistoryPredictionItem
import id.rashio.android.data.network.api.ApiService
import id.rashio.android.data.network.response.PredictionResponse
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class PredictionRepository @Inject constructor(
    private val apiService: ApiService,
    val tokenPreference: TokenPreference,
) : ViewModel() {


    suspend fun predictImage(photo: File): Result<PredictionResponse> {
        val token = tokenPreference.userData.first().token
        val authorization = "Bearer $token"

        val requestImageFile = photo.asRequestBody("image/jpeg".toMediaType())
        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "image",
            photo.name,
            requestImageFile
        )

        return apiService.predict(authorization, imageMultipart)
    }

    fun getHistory() = flow {
        val token = tokenPreference.userData.first().token
        val authorization = "Bearer $token"
        val userId = tokenPreference.userData.first().id

        val response = apiService.historyDetection(authorization, userId)
        emit(
            response.fold(
                onSuccess = { historyResponse ->
                    historyResponse.data.predictionLogs.sortedBy {
                        it.createdAt
                    }
                        .map {
                            HistoryPredictionItem(
                                result = it.result,
                                createdAt = it.createdAt,
                                imageUrl = it.imageUrl,
                                percentage = it.percentage
                            )
                        }
                },
                onFailure = {
                    emptyList()
                }
            )
        )
    }

}