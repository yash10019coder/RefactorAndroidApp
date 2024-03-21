package com.zobaze.zobazerefractortask.di


import androidx.multidex.BuildConfig
import com.zobaze.zobazerefractortask.data.backend.BaseUrl
import com.zobaze.zobazerefractortask.data.backend.api.EmployeeService
import com.zobaze.zobazerefractortask.data.backend.repository.EmployeeRepo
import com.zobaze.zobazerefractortask.data.backend.repository.EmployeeRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class Module {
    @Provides
    @BaseUrl
    fun provideBaseUrl() = "https://dummy.restapiexample.com/"

    @Provides
    fun provideOkhttpClient(): OkHttpClient {
        lateinit var loggingInteceptor: HttpLoggingInterceptor
        loggingInteceptor = HttpLoggingInterceptor()
        loggingInteceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .callTimeout(5, java.util.concurrent.TimeUnit.SECONDS)
            .addInterceptor(loggingInteceptor)
            .readTimeout(5, java.util.concurrent.TimeUnit.SECONDS)
            .writeTimeout(5, java.util.concurrent.TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideRetrofit(
        @BaseUrl baseUrl: String,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideEmployeeService(retrofit: Retrofit): EmployeeService {
        return retrofit.create(EmployeeService::class.java)
    }

    @Provides
    fun provideEmployeeRepository(
        employeeService: EmployeeService
    ): EmployeeRepo {
        return EmployeeRepoImpl(employeeService)
    }
}
