package presentation.settings_feature.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.example.project.core.StaticDateApi
import presentation.settings_feature.viewmodel.intents.BackIntent
import presentation.settings_feature.viewmodel.intents.NotificationsIntent
import presentation.settings_feature.viewmodel.intents.SetSettingsIntent

class SettingsViewModel(val date: StaticDateApi):ViewModel() {
    companion object{
        var settingsState by mutableStateOf(SettingsState())
    }
    fun processIntent(intents: SettingsIntents){
        when(intents){
            is SettingsIntents.Notifications -> {NotificationsIntent.execute()}
           // is SettingsIntents.Sounds -> {SoundsIntent.execute()}
            is SettingsIntents.Back -> {BackIntent.execute()}
            is SettingsIntents.SetScreen -> {SetSettingsIntent.execute()}
        }
    }
}