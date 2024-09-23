package presentation.profile_feature.viewmodel.intent

import org.example.project.core.StaticDate
import org.example.project.presentation.menu_feature.viewmodel.MenuViewModel
import org.example.project.presentation.profile_feature.viewmodel.ProfileViewModel
import org.example.project.presentation.wallets_feature.list_wallets_feature.screen.ListWalletsScreen

object WalletsIntent {
    fun execute(){
        ProfileViewModel(StaticDate).date.isUsedListWallet.value = true
        StaticDate.navigator.push(ListWalletsScreen)
    }
}