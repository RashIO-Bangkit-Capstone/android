package id.rashio.android.data.repository

import id.rashio.android.data.network.api.ApiService
import id.rashio.android.data.network.response.DataItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ArticleRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun getArticlesHome(): Flow<List<DataItem>> = flow {
        val response = apiService.getArticles()
        emit(
            response.fold(
                onSuccess = { articleResponse ->
                    articleResponse.data.sortedBy {
                        it.id
                    }.take(3)
                },
                onFailure = {
                    emptyList()
                }
            )
        )
    }
}