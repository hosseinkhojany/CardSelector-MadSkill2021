package com.example.myapplication.di

import com.example.myapplication.data.repositories.CardRepository
import com.example.myapplication.data.datasource.remote.CardDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

/**
 * this class provides repositories for use cases
 */
@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
  /**
   * provide CardRepository in [ViewModelScoped]
   * This means that a sample instance of [CardRepository] taken in ViewModel LifeCycle
   */
  @Provides
  @ViewModelScoped
  fun provideCardRepository(
    networkClient: CardDataSource,
    coroutineDispatcher: CoroutineDispatcher
  ): CardRepository {
    return CardRepository(networkClient, coroutineDispatcher)
  }
}
