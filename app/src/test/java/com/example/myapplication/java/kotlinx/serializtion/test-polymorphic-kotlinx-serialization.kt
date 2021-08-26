// This file was automatically generated from polymorphism.md by Knit tool. Do not edit.

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic


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

val module = SerializersModule {
    polymorphic(BaseCard::class) {
        subclass(Picture::class, Picture.serializer())
        subclass(Sound::class, Sound.serializer())
        subclass(Vibrate::class, Vibrate.serializer())
    }
}

val format = Json {
    classDiscriminator = "code"
    serializersModule = module
}

fun main() {

    val cards2 = format.decodeFromString<Cards>("""
 {
  "cards": [
    {
      "code": 1,
      "title": "Exercise",
      "description": "Exercise on a regular basis.",
      "tag": "sport"
    },
    {
      "code": 0,
      "title": "Painting",
      "description": "Look at this beautiful painting",
      "image": "https://raw.githubusercontent.com/AmirrezaRotamian/Tech-Challenge/master/assets/sky.jpg",
      "tag": "art"
    },
    {
      "code": 2,
      "title": "Let's have fun",
      "description": "Listen to the music",
      "sound": "https://raw.githubusercontent.com/AmirrezaRotamian/Tech-Challenge/master/assets/sound.mp3",
      "tag": "fun"
    },
    {
      "code": 1,
      "title": "Hey!",
      "description": "Have you called your parents lately!",
      "tag": "fun"
    },
    {
      "code": 0,
      "title": "Sports",
      "description": "Have you ever played one of theses sports?",
      "image": "https://raw.githubusercontent.com/AmirrezaRotamian/Tech-Challenge/master/assets/sport.jpg",
      "tag": "sport"
    }
  ]
}
    """)
    println(cards2.cards)


}
