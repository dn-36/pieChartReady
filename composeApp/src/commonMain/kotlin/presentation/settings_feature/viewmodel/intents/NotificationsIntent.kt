package presentation.settings_feature.viewmodel.intents

import ServiceLocator
import androidx.compose.runtime.mutableStateOf
import database.Profile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.example.project.core.StaticDate
import piechart.composeapp.generated.resources.Res
import piechart.composeapp.generated.resources.clicked_cub
import piechart.composeapp.generated.resources.empty_cub
import piechart.composeapp.generated.resources.man_ava
import presentation.settings_feature.viewmodel.SettingsViewModel

object NotificationsIntent {
    fun execute(){
        if(SettingsViewModel.settingsState.imgNotifications.value == Res.drawable.empty_cub) {
            SettingsViewModel.settingsState = SettingsViewModel.settingsState.copy(
                imgNotifications = mutableStateOf(Res.drawable.clicked_cub),
                toast = mutableStateOf(true)
            )
            StaticDate.notifications = true
            ServiceLocator.notifications.requestNotificationPermission {
                ServiceLocator.notifications.toggleNotifications()
            }



        }
        else{
            SettingsViewModel.settingsState = SettingsViewModel.settingsState.copy(
                imgNotifications = mutableStateOf(Res.drawable.empty_cub),
            )
            StaticDate.notifications = false
            ServiceLocator.notifications.requestNotificationPermission {
                ServiceLocator.notifications.toggleNotifications()
            }

        }

        val name = SettingsViewModel(StaticDate).date.name
        val gender = if(SettingsViewModel(StaticDate).date.gender == Res.drawable.man_ava) "man" else "woman"
        val notifications = SettingsViewModel(StaticDate).date.notifications
        val sounds = SettingsViewModel(StaticDate).date.sounds
        CoroutineScope(Dispatchers.IO).launch {

            ServiceLocator.profileDao.upsert(
                Profile(
                    name = name,
                    gender = gender,
                    notifications = notifications,
                    sounds = sounds
                )
            )
        }
        SettingsViewModel(StaticDate).date.isUsedSettings.value = true
    }
}