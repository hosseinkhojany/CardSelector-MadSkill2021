package com.example.myapplication.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

/**
 * this class provide coroutine global configs
 */
@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

  /**
   * provide a singleton Coroutine IO dispatcher
   * [Dispatchers.IO] used for networking or read and write to database
   **/
  @Provides
  @Singleton
  fun provideIODispatcher(): CoroutineDispatcher {
    return Dispatchers.IO
  }
}
