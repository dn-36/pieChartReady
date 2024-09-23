package org.example.project.presentation.registerAndStart.add_wallet_feature.viewmodel.intents

import ServiceLocator
import androidx.compose.runtime.mutableStateOf
import database.Person
import database.Profile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.example.project.core.StaticDate
import org.example.project.presentation.menu_feature.screen.MenuScreen
import org.example.project.presentation.menu_feature.utils.getCurrentMonth
import org.example.project.presentation.menu_feature.utils.getCurrentYear
import org.example.project.presentation.menu_feature.utils.getTodayDay
import org.example.project.presentation.profile_feature.viewmodel.ProfileViewModel
import org.example.project.presentation.registerAndStart.add_wallet_feature.viewmodel.AddWalletViewModel
import org.example.project.utils.SumWallet
import org.example.project.utils.Wallets
import piechart.composeapp.generated.resources.Res
import piechart.composeapp.generated.resources.man_ava

object FinishIntent {
    fun execute(name:String,sum:String){
        if(name.length == 0 || sum.length == 0){
            AddWalletViewModel.addWalletState = AddWalletViewModel.addWalletState.copy(
                toast = mutableStateOf(true)
            )
        }
        else {

            AddWalletViewModel(StaticDate).date.listWallets.add(Wallets(sum = sum,name = name, currency = AddWalletViewModel.addWalletState.currency.value,
            listTransactionsIncome = mutableListOf()
                , listTransactionsExpense = mutableListOf(),mutableListOf(SumWallet(
                    type = "Add", date = "${getCurrentMonth()} ${getTodayDay()}, ${getCurrentYear()}",sum = "+${sum}"))
            ))

            val img = AddWalletViewModel(StaticDate).date.photo
            val dao = ServiceLocator.profileDao
            val name = AddWalletViewModel(StaticDate).date.name
            var notifications  = AddWalletViewModel(StaticDate).date.notifications
            var sounds  = AddWalletViewModel(StaticDate).date.notifications
            val gender = if(AddWalletViewModel(StaticDate).date.gender == Res.drawable.man_ava) "man" else "woman"


            CoroutineScope(Dispatchers.IO).launch {
                try {
                    StaticDate.listWallets.forEach {
                        ServiceLocator.peopleDao.upsert(Person(wallet = it))
                        println("${it}")
                    }
                    ServiceLocator.profileDao.upsert(Profile(name = name, gender = gender, notifications = notifications, sounds = sounds))

                } catch (e: Exception) {
                    println("h \n d \n h \n" +
                            " d")
                    println("RoomError Error saving to database")
                    println("h \n d \n h \n" +
                            " d")
                }
                withContext(Dispatchers.Main) {
                StaticDate.navigator.push(MenuScreen)}


            }
        }
    }
}