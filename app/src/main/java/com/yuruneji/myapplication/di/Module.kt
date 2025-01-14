package com.yuruneji.myapplication.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.yuruneji.myapplication.BuildConfig
import com.yuruneji.myapplication.common.CipherExtractor
import com.yuruneji.myapplication.common.enum.ApiType
import com.yuruneji.myapplication.common.service.NetworkService
import com.yuruneji.myapplication.data.local.db.AppDatabase
import com.yuruneji.myapplication.data.local.db.LogDao
import com.yuruneji.myapplication.data.local.setting.AppPreferences
import com.yuruneji.myapplication.data.local.setting.BaseDataStore
import com.yuruneji.myapplication.data.local.setting.LogViewDataStore
import com.yuruneji.myapplication.data.remote.App2Service
import com.yuruneji.myapplication.data.remote.AppService
import com.yuruneji.myapplication.data.repository.App2RepositoryImpl
import com.yuruneji.myapplication.data.repository.AppRepositoryImpl
import com.yuruneji.myapplication.data.repository.LogRepositoryImpl
import com.yuruneji.myapplication.domain.repository.App2Repository
import com.yuruneji.myapplication.domain.repository.AppRepository
import com.yuruneji.myapplication.domain.repository.LogRepository
import com.yuruneji.myapplication.domain.usecase.LogFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author henriko
 * @version 1.0
 */
@Module
@InstallIn(SingletonComponent::class)
object Module {


    /** ネットワーク状態 */
    @Provides
    @Singleton
    fun provideNetworkService(@ApplicationContext context: Context): NetworkService {
        return NetworkService(context)
    }


    /** 暗号化 */
    @Provides
    @Singleton
    fun provideCipherExtractor(@ApplicationContext context: Context): CipherExtractor =
        CipherExtractor(context)


    /** 設定 */
    @Provides
    @Singleton
    fun provideAppPreferences(@ApplicationContext context: Context): AppPreferences =
        AppPreferences(context)

    // @Provides
    // @Singleton
    // fun provideCameraPreferences(@ApplicationContext context: Context): CameraPreferences =
    //     CameraPreferences(context)

    @Provides
    @Singleton
    fun provideBaseDataStore(@ApplicationContext context: Context): BaseDataStore {
        return BaseDataStore(context)
    }

    @Provides
    @Singleton
    fun provideLogViewDataStore(@ApplicationContext context: Context): LogViewDataStore {
        return LogViewDataStore(context)
    }


    /** DB */
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, name = "app_db").build()

    @Provides
    @Singleton
    fun provideLogDao(db: AppDatabase) = db.logDao()

    @Provides
    @Singleton
    fun provideLogRepository(logDao: LogDao): LogRepository {
        return LogRepositoryImpl(logDao)
    }


    /** ログ */
    @Provides
    @Singleton
    fun provideLogFile(logFile: LogDao): LogFile {
        return LogFile(logFile)
    }


    /** Json */
    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()


    /** HTTP */
    @Singleton
    @Provides
    fun provideApp2Service(moshi: Moshi, pref: AppPreferences): App2Service {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(90, TimeUnit.SECONDS)
            .readTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)
            .build()

        val url = when (pref.apiType) {
            ApiType.DEVELOP.no -> BuildConfig.API_URL_DEVELOP
            ApiType.STAGING.no -> BuildConfig.API_URL_STAGING
            ApiType.PRODUCTION.no -> BuildConfig.API_URL_PRODUCTION
            else -> BuildConfig.API_URL_BASE
        }

        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(
                MoshiConverterFactory.create(moshi)
            ).build()
            .create(App2Service::class.java)
    }

    @Provides
    fun provideApp2Repository(api: App2Service): App2Repository = App2RepositoryImpl(api)


}


@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    /** HTTP */
    @Provides
    fun provideHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(90, TimeUnit.SECONDS)
            .readTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)
            .build()

    @Provides
    fun provideAppService(okHttpClient: OkHttpClient, moshi: Moshi, pref: AppPreferences): AppService {
        val url = when (pref.apiType) {
            ApiType.DEVELOP.no -> BuildConfig.API_URL_DEVELOP
            ApiType.STAGING.no -> BuildConfig.API_URL_STAGING
            ApiType.PRODUCTION.no -> BuildConfig.API_URL_PRODUCTION
            else -> BuildConfig.API_URL_BASE
        }

        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(
                MoshiConverterFactory.create(moshi)
            ).build()
            .create(AppService::class.java)
    }

    @Provides
    fun provideAppRepository(api: AppService): AppRepository = AppRepositoryImpl(api)

}
