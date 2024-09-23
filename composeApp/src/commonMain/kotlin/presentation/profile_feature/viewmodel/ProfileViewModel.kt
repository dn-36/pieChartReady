package org.example.project.presentation.profile_feature.viewmodel

import androidx.lifecycle.ViewModel
import org.example.project.core.StaticDateApi
import presentation.profile_feature.viewmodel.intent.AddTransactionIntent
import presentation.profile_feature.viewmodel.intent.HomeIntent
import presentation.profile_feature.viewmodel.intent.SetProfileIntent
import presentation.profile_feature.viewmodel.intent.SettingsIntent
import presentation.profile_feature.viewmodel.intent.StatisticIntent
import presentation.profile_feature.viewmodel.intent.WalletsIntent

class ProfileViewModel(val date: StaticDateApi): ViewModel() {
    fun processIntent(intents: ProfileIntents){
        when(intents){
            is ProfileIntents.SetScreen -> {SetProfileIntent.execute(intents.images)}
            is ProfileIntents.Home -> {HomeIntent.execute()}
            is ProfileIntents.Statistic -> {StatisticIntent.execute()}
            is ProfileIntents.Wallets -> {WalletsIntent.execute()}
            is ProfileIntents.Settings -> {SettingsIntent.execute()}
            is ProfileIntents.AddTransaction -> {AddTransactionIntent.execute()}
        }
    }
}