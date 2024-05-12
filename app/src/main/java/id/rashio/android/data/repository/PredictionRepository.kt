package id.rashio.android.data.repository

import id.rashio.android.data.local.preferences.TokenPreference
import id.rashio.android.data.network.api.ApiService
import id.rashio.android.data.network.response.PredictionResponse
import kotlinx.coroutines.flow.first
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class PredictionRepository @Inject constructor(
    private val apiService: ApiService,
    private val tokenPreference: TokenPreference
) {

    suspend fun predictImage(photo: File): Result<PredictionResponse> {
        val requestImageFile = photo.asRequestBody("image/jpeg".toMediaType())
        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "image",
            photo.name,
            requestImageFile
        )

        val authorization = "Bearer ${tokenPreference.userData.first().token}"

        return apiService.predict(authorization, imageMultipart)
    }

}