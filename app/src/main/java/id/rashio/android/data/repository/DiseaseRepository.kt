package id.rashio.android.data.repository

import id.rashio.android.data.network.api.ApiService
import id.rashio.android.data.network.response.DiseaseResponse
import javax.inject.Inject

class DiseaseRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getDisease(name: String): Result<DiseaseResponse> {
        return apiService.getDisease(name)
    }
}