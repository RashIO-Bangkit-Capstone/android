package id.rashio.android.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.rashio.android.data.local.preferences.TokenPreference
import id.rashio.android.data.local.preferences.tokenDataStore
import id.rashio.android.data.network.api.ApiConfig
import id.rashio.android.data.network.api.ApiService

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

}