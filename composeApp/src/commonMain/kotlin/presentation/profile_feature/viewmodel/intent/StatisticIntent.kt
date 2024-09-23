package presentation.profile_feature.viewmodel.intent

import org.example.project.core.StaticDate
import org.example.project.presentation.menu_feature.viewmodel.MenuViewModel
import org.example.project.presentation.profile_feature.viewmodel.ProfileViewModel
import org.example.project.presentation.statistic_feature.screen.StatisticScreen

object StatisticIntent {
    fun execute(){
        ProfileViewModel(StaticDate).date.isUsedStatistic.value = true
        ProfileViewModel(StaticDate).date.isUsedTransactions.value = true
        StaticDate.navigator.push(StatisticScreen)
    }
}