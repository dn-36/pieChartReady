package org.example.project.presentation.profile_feature.viewmodel

import androidx.compose.ui.graphics.ImageBitmap

sealed class ProfileIntents {
    data class SetScreen(val images:MutableList<ImageBitmap>):ProfileIntents()
    object Home:ProfileIntents()
    object Statistic:ProfileIntents()
    object Wallets:ProfileIntents()
    object Settings:ProfileIntents()
    object AddTransaction:ProfileIntents()
}