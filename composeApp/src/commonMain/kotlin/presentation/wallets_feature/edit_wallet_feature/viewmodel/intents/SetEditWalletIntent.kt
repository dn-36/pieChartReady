package org.example.project.presentation.wallets_feature.edit_wallet_feature.viewmodel.intents

import androidx.compose.runtime.mutableStateOf
import org.example.project.core.StaticDate
import org.example.project.presentation.wallets_feature.edit_wallet_feature.viewmodel.EditWalletViewModel
import piechart.composeapp.generated.resources.Res
import piechart.composeapp.generated.resources.circle
import piechart.composeapp.generated.resources.clicked_circle


object SetEditWalletIntent {
    fun execute() {
        if (EditWalletViewModel(StaticDate).date.isUsedEditWallet.value) {
            EditWalletViewModel(StaticDate).date.isUsedEditWallet.value = false
            val newList = mutableListOf(
                Res.drawable.circle, Res.drawable.circle,
                Res.drawable.circle, Res.drawable.circle, Res.drawable.circle, Res.drawable.circle,
                Res.drawable.circle, Res.drawable.circle, Res.drawable.circle,
            )
            when (EditWalletViewModel(StaticDate).date.listWallets[EditWalletViewModel(StaticDate).date.indexDetailWallet.value].currency) {
                "0" -> {
                    newList[0] = Res.drawable.clicked_circle
                }

                "1" -> {
                    newList[1] = Res.drawable.clicked_circle
                }

                "2" -> {
                    newList[2] = Res.drawable.clicked_circle
                }

                "3" -> {
                    newList[3] = Res.drawable.clicked_circle
                }

                "4" -> {
                    newList[4] = Res.drawable.clicked_circle
                }

               "5" -> {
                    newList[5] = Res.drawable.clicked_circle
                }

                "6" -> {
                    newList[6] = Res.drawable.clicked_circle
                }

                "7" -> {
                    newList[7] = Res.drawable.clicked_circle
                }

                "8" -> {
                    newList[8] = Res.drawable.clicked_circle
                }
            }
            EditWalletViewModel.editWalletState = EditWalletViewModel.editWalletState.copy(
                currency = mutableStateOf(EditWalletViewModel(StaticDate).date.listWallets[EditWalletViewModel(
                        StaticDate
                    ).date.indexDetailWallet.value].currency
                ),
                listCurrency = newList
            )
        }
    }
}