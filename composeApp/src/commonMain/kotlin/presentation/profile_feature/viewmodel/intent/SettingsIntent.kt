package presentation.profile_feature.viewmodel.intent

import org.example.project.core.StaticDate
import org.example.project.presentation.profile_feature.viewmodel.ProfileViewModel
import presentation.settings_feature.screen.SettingsScreen

object SettingsIntent {
    fun execute(){
        ProfileViewModel(StaticDate).date.isUsedSettings.value = true
        StaticDate.navigator.push(SettingsScreen)
    }
}