package id.rashio.android.ui.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.rashio.android.data.local.preferences.TokenPreference
import id.rashio.android.data.local.preferences.UserData
import id.rashio.android.data.model.BookmarkableArticle
import id.rashio.android.data.repository.ArticleRepository
import id.rashio.android.data.repository.WeatherRepository
import id.rashio.android.domain.location.LocationTracker
import id.rashio.android.domain.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val articleRepository: ArticleRepository,
    private val weatherRepository: WeatherRepository,
    private val locationTracker: LocationTracker,
    private val tokenPreference: TokenPreference,
) : ViewModel() {

    var state by mutableStateOf(WeatherState())
        private set

    fun loadWeatherInfo() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            delay(5000)
            locationTracker.getCurrentLocation()?.let { location ->
                when (val result =
                    weatherRepository.getWeatherData(location.latitude, location.longitude)) {
                    is Resource.Success -> {
                        state = state.copy(
                            weatherInfo = result.data,
                            isLoading = false,
                            error = null
                        )
                    }

                    is Resource.Error -> {
                        state = state.copy(
                            weatherInfo = null,
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            } ?: kotlin.run {
                state = state.copy(
                    isLoading = false,
                    error = "Couldn't retrieve location. Make sure to grant permission and enable GPS."
                )
            }
        }
    }

    fun bookmarkArticle(bookmarkableArticle: BookmarkableArticle) {
        viewModelScope.launch {
            articleRepository.bookmarkArticle(bookmarkableArticle)
        }
    }

    val articles = articleRepository.getArticlesHome()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
            initialValue = emptyList()
        )


    val userData = tokenPreference.userData.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
        initialValue = UserData(name = "", email = "", phoneNumber = "", token = "", id = "")
    )
}
