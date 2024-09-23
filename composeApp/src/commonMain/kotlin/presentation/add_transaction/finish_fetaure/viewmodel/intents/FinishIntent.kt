package org.example.project.presentation.add_transaction.finish_fetaure.viewmodel.intents

import database.Person
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.example.project.core.StaticDate
import org.example.project.presentation.add_transaction.utils.DataTransaction
import org.example.project.presentation.add_transaction.finish_fetaure.viewmodel.FinishViewModel
import org.example.project.presentation.menu_feature.screen.MenuScreen
import org.example.project.presentation.wallets_feature.detail_wallet_feature.viewmodel.DetailWalletViewModel


object FinishIntent {
    fun execute(){
        if (DataTransaction.incomeOrExpense.value == "Expense"){
            FinishViewModel(StaticDate).date.listWallets[FinishViewModel(StaticDate).date.indexWalletNow.value].listTransactionsExpense.add(
                FinishViewModel.finishState.transaction.value
            )
           /* FinishViewModel(StaticDate).date.listTransactionsExpense.add(
                FinishViewModel.finishState.transaction.value
            )*/
        }
        else if(DataTransaction.incomeOrExpense.value == "Income"){
            FinishViewModel(StaticDate).date.listWallets[FinishViewModel(StaticDate).date.indexWalletNow.value].listTransactionsIncome.add(
                FinishViewModel.finishState.transaction.value
            )
        }
        val indexNow = FinishViewModel(StaticDate).date.indexDetailWallet.value+1
        val editedWallet = FinishViewModel(StaticDate).date.listWallets[DetailWalletViewModel(StaticDate).date.indexDetailWallet.value]
        CoroutineScope(Dispatchers.IO).launch  {
            ServiceLocator.peopleDao.update(Person( id = indexNow, wallet = editedWallet))
        }





         DataTransaction.incomeOrExpense.value = ""
         DataTransaction.sum.value = ""
         DataTransaction.category.value = ""
         DataTransaction.name.value = ""
         DataTransaction.month.value = ""
         DataTransaction.day.value = ""
         DataTransaction.year.value = ""
         DataTransaction.icon.value = ""

        StaticDate.navigator.push(MenuScreen)
    }
}