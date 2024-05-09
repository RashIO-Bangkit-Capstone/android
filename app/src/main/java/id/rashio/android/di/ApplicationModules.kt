package id.rashio.android.di

import android.app.Application
import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.rashio.android.data.local.preferences.TokenPreference
import id.rashio.android.data.local.preferences.tokenDataStore
import id.rashio.android.data.local.room.ArticleDatabase
import id.rashio.android.data.network.api.ApiConfig
import id.rashio.android.data.network.api.ApiService
import id.rashio.android.data.network.api.weather_api.WeatherAPI
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModules {

    @Provides
    fun provideApiService(): ApiService = ApiConfig.getApiService()

    @Provides
    fun provideTokenPreference(@ApplicationContext context: Context): TokenPreference =
        context.tokenDataStore.let {
            TokenPreference.getInstance(it)
        }

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherAPI {
        return Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }

    @Provides
    fun provideDatabaseArticle(@ApplicationContext context: Context): ArticleDatabase {
        return ArticleDatabase.getDatabase(context)
    }

    @Provides
    fun provideDaoArticle(database: ArticleDatabase) = database.bookmarkArticleDao()

}