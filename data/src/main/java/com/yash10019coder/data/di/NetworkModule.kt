import com.yash10019coder.data.backend.BaseUrl
import com.yash10019coder.data.backend.api.EmployeeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @BaseUrl
    fun provideBaseUrl() = "https://dummy.restapiexample.com/api/v1"

//    @Provides
//    fun provideOkhttpClient(): OkHttpClient {
//        return OkHttpClient.Builder()
//            .callTimeout(5, java.util.concurrent.TimeUnit.SECONDS)
//            .readTimeout(5, java.util.concurrent.TimeUnit.SECONDS)
//            .writeTimeout(5, java.util.concurrent.TimeUnit.SECONDS)
//            .build()
//    }

    @Provides
    fun provideRetrofit(
        @BaseUrl baseUrl: String,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideEmployeeService(retrofit: Retrofit): EmployeeService {
        return retrofit.create(EmployeeService::class.java)
    }
}
