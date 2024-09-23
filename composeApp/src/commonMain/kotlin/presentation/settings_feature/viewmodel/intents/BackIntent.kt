package presentation.settings_feature.viewmodel.intents

import org.example.project.core.StaticDate
import org.example.project.presentation.profile_feature.screen.ProfileScreen
import org.example.project.presentation.profile_feature.viewmodel.ProfileViewModel
import org.example.project.presentation.wallets_feature.list_wallets_feature.screen.ListWalletsScreen

object BackIntent {
    fun execute(){
        StaticDate.navigator.push(ProfileScreen)
    }
}