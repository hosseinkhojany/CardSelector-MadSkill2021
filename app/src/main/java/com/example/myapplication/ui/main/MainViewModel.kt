package com.example.myapplication.ui.main

import androidx.annotation.WorkerThread
import androidx.databinding.Bindable
import com.example.myapplication.data.model.response.BaseCard
import com.example.myapplication.data.repositories.CardRepository
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val cardRepository: CardRepository
) : BindingViewModel() {

    @get:Bindable
    var isLoading: Boolean by bindingProperty(false)
        private set

    @get:Bindable
    var isNewCardComing: Boolean by bindingProperty(true)
        private set

    //this variable
    @get:Bindable
    var toastMessage: String? by bindingProperty(null)
        private set

    //this variable hold the selected card and send value to []
    @get:Bindable
    var fetchCards: BaseCard? by bindingProperty(null)
        private set

    init {
        Timber.d("init MainViewModel")
    }


    @WorkerThread
    fun showRandomCard() {
        if (!isLoading) {
            CoroutineScope(Dispatchers.IO).launch {
                cardRepository.getCards(
                    onStart = { isLoading = true; isNewCardComing = isLoading },
                    onComplete = { isLoading = false; isNewCardComing = isLoading },
                    onError = { toastMessage = it }
                ).collect {
                    fetchCards = it
                }
            }
        }
    }


}