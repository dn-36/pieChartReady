package org.example.project.presentation.wallets_feature.list_wallets_feature.viewmodel.intents

import ServiceLocator
import androidx.compose.runtime.mutableStateOf
import database.Person
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.example.project.core.StaticDate
import org.example.project.presentation.wallets_feature.list_wallets_feature.viewmodel.ListWalletsViewModel

object DeleteWalletIntent {
    fun execute(index:Int){
         if(index == ListWalletsViewModel(StaticDate).date.indexWalletNow.value && index == 0
            && ListWalletsViewModel(StaticDate).date.listWallets.size == 1){

            ListWalletsViewModel.listWalletsState = ListWalletsViewModel.listWalletsState.copy(
                toast = mutableStateOf(true))

         }
        else {
            val list = ListWalletsViewModel(StaticDate).date.listWallets.toMutableList()
             val deletedWallet = list[index]
             val listId = mutableListOf<Int>()

             CoroutineScope(Dispatchers.IO).launch  {
                 ServiceLocator.peopleDao.getAllPeople().forEach {
                     listId.add(it.id)
                 }
                 ServiceLocator.peopleDao.delete(Person(id = listId[index],wallet = deletedWallet))
                 println("333")
                 println("333")
                 println("333")
                 println("${ServiceLocator.peopleDao.getAllPeople()}")
                 println("${index}")
                 println("333")
                 println("333")
                 println("333")
             }



             list.removeAt(index)

             if (index == ListWalletsViewModel(StaticDate).date.indexWalletNow.value && index != 0) {
                 ListWalletsViewModel(StaticDate).date.indexWalletNow.value -= 1
             } else if (index == ListWalletsViewModel(StaticDate).date.indexWalletNow.value && index == 0
                 && ListWalletsViewModel(StaticDate).date.listWallets.size != 1
             ) {
                 ListWalletsViewModel(StaticDate).date.indexWalletNow.value += 1
             }

             ListWalletsViewModel(StaticDate).date.listWallets = list
             ListWalletsViewModel(StaticDate).date.isUsedListWallet.value = true
         }
    }
}