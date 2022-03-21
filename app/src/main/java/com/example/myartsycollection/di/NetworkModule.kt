package com.example.myartsycollection.di

import com.example.myartsycollection.network.AppClient
import com.example.myartsycollection.network.AppService
import com.example.myartsycollection.network.HttpRequestInterceptor
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(HttpRequestInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.artsy.net/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAppService(retrofit: Retrofit): AppService {
        return retrofit.create(AppService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppClient(appService: AppService): AppClient {
        return AppClient(appService)
    }
}