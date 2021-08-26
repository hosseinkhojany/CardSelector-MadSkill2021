package com.example.myapplication.data.model.response

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Polymorphic
@Serializable
abstract class BaseCard

@Serializable
@SerialName("0")
data class Picture(
    val title: String,
    val description: String,
    val tag: String,
    val image:String
) : BaseCard()

@Serializable
@SerialName("1")
data class Vibrate(
    val title: String,
    val description: String,
    val tag: String,
) : BaseCard()


@Serializable
@SerialName("2")
data class Sound(
    val title: String,
    val description: String,
    val tag: String,
    val sound: String
): BaseCard()

@Serializable
class Cards{
    val cards = listOf<BaseCard>()
}

