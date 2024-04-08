package com.coolgirl.madmeditation.screens.Profile

sealed class ProfileState {
    data class ImageResource(val resourceId: Int?) : ProfileState()
    data class ImageUri(val uri: String?) : ProfileState()
}