package org.example.project.presentation.wallets_feature.add_new_wallet_feature.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import org.jetbrains.compose.resources.DrawableResource
import piechart.composeapp.generated.resources.Res
import piechart.composeapp.generated.resources.circle
import piechart.composeapp.generated.resources.clicked_circle
import piechart.composeapp.generated.resources.one_currency

data class AddNewWalletState(
    var listCurrency:MutableList<DrawableResource> = mutableListOf(
        Res.drawable.clicked_circle, Res.drawable.circle,
        Res.drawable.circle, Res.drawable.circle, Res.drawable.circle, Res.drawable.circle,
        Res.drawable.circle, Res.drawable.circle, Res.drawable.circle,),
    val toast: MutableState<Boolean> = mutableStateOf(false),
    val currency: MutableState<String> = mutableStateOf(""),
)
