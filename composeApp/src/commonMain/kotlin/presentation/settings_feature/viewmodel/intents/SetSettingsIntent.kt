package presentation.settings_feature.viewmodel.intents

import androidx.compose.runtime.mutableStateOf
import org.example.project.core.StaticDate
import piechart.composeapp.generated.resources.Res
import piechart.composeapp.generated.resources.clicked_cub
import piechart.composeapp.generated.resources.empty_cub
import presentation.settings_feature.viewmodel.SettingsViewModel

object SetSettingsIntent {
    fun execute(){
        if(SettingsViewModel(StaticDate).date.isUsedSettings.value) {
            SettingsViewModel(StaticDate).date.isUsedSettings.value = false
            val imgNotifications =
                if (SettingsViewModel(StaticDate).date.notifications) Res.drawable.clicked_cub else Res.drawable.empty_cub
            val imgSounds =
                if (SettingsViewModel(StaticDate).date.sounds) Res.drawable.clicked_cub else Res.drawable.empty_cub
            SettingsViewModel.settingsState = SettingsViewModel.settingsState.copy(
                imgSounds = mutableStateOf(imgSounds),
                imgNotifications = mutableStateOf(imgNotifications)
            )
        }
    }
}