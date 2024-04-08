package com.coolgirl.madmeditation.screens.Profile

sealed class ImageData {
    data class ImageResource(val resourceId: Int) : ImageData()
    data class ImageUri(val uri: String) : ImageData()
}