package presentation.settings_feature.viewmodel

sealed class SettingsIntents {
    object Notifications:SettingsIntents()
    //object Sounds:SettingsIntents()
    object Back:SettingsIntents()
    object SetScreen:SettingsIntents()
}