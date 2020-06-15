package com.core.model

data class Post (
    val image: ImageEnum,
    val title: String,
    val distance: String,
    val shower: String,
    val food: String,
    val stars: Float
)

enum class ImageEnum {
    IPIRANGA,
    BR,
    SHELL
}