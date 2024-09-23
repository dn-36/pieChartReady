package presentation.settings_feature.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import org.jetbrains.compose.resources.DrawableResource
import piechart.composeapp.generated.resources.Res
import piechart.composeapp.generated.resources.empty_cub

data class SettingsState(
    var imgNotifications:MutableState<DrawableResource> = mutableStateOf(Res.drawable.empty_cub),
    var imgSounds:MutableState<DrawableResource> = mutableStateOf(Res.drawable.empty_cub),
    var toast:MutableState<Boolean> = mutableStateOf(false)
)
