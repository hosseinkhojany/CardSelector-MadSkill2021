package com.example.myapplication.di

import com.example.myapplication.net.HttpRequestInterceptor
import com.example.myapplication.data.datasource.remote.CardDataSource
import com.example.myapplication.data.model.response.*
import com.example.myapplication.net.NetworkService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.*
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * this class provides app networking
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  /**
   * provide a singleton OkHttpClient
   * [OkHttpClient] is factory for [calls][Call], which can be used to send HTTP requests and read their responses.
   **/
  @Provides
  @Singleton
  fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(HttpRequestInterceptor())
      .build()
  }


  /**
   * provide a singleton Retrofit comes with all required configurations
   */
  @ExperimentalSerializationApi
  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .client(okHttpClient)
      .baseUrl("https://raw.githubusercontent.com/AmirrezaRotamian/Tech-Challenge/master/")
      .addConverterFactory(
        Json {
          /**
           * Name of the class descriptor property for polymorphic serialization.
           * "type" by default.
           */
          classDiscriminator = "code"
          /**
           * Module with contextual and polymorphic serializers to be used in the resulting [Json] instance.
           */
          serializersModule = SerializersModule {
            /**
             * Creates a builder to register subclasses of a given [baseClass] for polymorphic serialization.
             * If [baseSerializer] is not null, registers it as a serializer for [baseClass],
             * which is useful if the base class is serializable itself. To register subclasses,
             * [PolymorphicModuleBuilder.subclass] builder function can be used.
             *
             * If a serializer already registered for the given KClass in the given scope, an [IllegalArgumentException] is thrown.
             * To override registered serializers, combine built module with another using [SerializersModule.overwriteWith].
             *
             * @see PolymorphicSerializer
             */
            polymorphic(BaseCard::class) {
              /**
               * Registers a [subclass] [serializer] in the resulting module under the [base class][Base].
               */
              subclass(Picture::class, Picture.serializer())
              subclass(Sound::class, Sound.serializer())
              subclass(Vibrate::class, Vibrate.serializer())
            }
          }
        }.
          /**
           * Return a [Converter.Factory] which uses Kotlin serialization for string-based payloads.
           */
        asConverterFactory("application/json".toMediaType()))
      .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory.create())
      .build()
  }

  /**
   * provide a singleton NetworkService.
   * Create an implementation of the API endpoints defined by the {@code service} interface.
   *
   */
  @Provides
  @Singleton
  fun provideNetworkService(retrofit: Retrofit): NetworkService {
    return retrofit.create(NetworkService::class.java)
  }

  /**
   * provide a singleton from [CardDataSource]
   */
  @Provides
  @Singleton
  fun provideNetworkClient(networkService: NetworkService): CardDataSource {
    return CardDataSource(networkService)
  }
}
