package org.example.project.presentation.add_transaction.finish_fetaure.viewmodel.intents

import androidx.compose.runtime.mutableStateOf
import org.example.project.presentation.add_transaction.utils.DataTransaction
import org.example.project.presentation.add_transaction.finish_fetaure.viewmodel.FinishViewModel
import org.example.project.utils.Transaction
import androidx.compose.ui.graphics.Color
import piechart.composeapp.generated.resources.Res
import piechart.composeapp.generated.resources.car
import piechart.composeapp.generated.resources.card
import piechart.composeapp.generated.resources.cup
import piechart.composeapp.generated.resources.food
import piechart.composeapp.generated.resources.music
import piechart.composeapp.generated.resources.people
import piechart.composeapp.generated.resources.phone
import piechart.composeapp.generated.resources.photoapp
import piechart.composeapp.generated.resources.planet
import piechart.composeapp.generated.resources.two_yellow_phone
import piechart.composeapp.generated.resources.yellow_calendar
import piechart.composeapp.generated.resources.yellow_home

object SetFinishIntent {
    fun execute(){

        val sign = if(DataTransaction.incomeOrExpense.value == "Expense")"-" else "+"

        val color = if(DataTransaction.incomeOrExpense.value == "Expense") Color.Red else Color(0xFFF8DB1C)

        val img = when(DataTransaction.icon.value){
            "0" -> { Res.drawable.cup}
            "1" -> { Res.drawable.photoapp}
            "2" -> { Res.drawable.card}
            "3" -> { Res.drawable.music}
            "4" -> { Res.drawable.people}
            "5" -> { Res.drawable.planet}
            "6" -> { Res.drawable.yellow_home}
            "7" -> { Res.drawable.yellow_calendar}
            "8" -> { Res.drawable.phone}
            "9" -> { Res.drawable.two_yellow_phone}
            "10" -> { Res.drawable.food}
            else -> { Res.drawable.car}
        }
        FinishViewModel.finishState = FinishViewModel.finishState.copy(
            transaction = mutableStateOf(Transaction(img = DataTransaction.icon.value,category = DataTransaction.category.value, name = DataTransaction.name.value
            , month = DataTransaction.month.value,day = DataTransaction.day.value,
                year = DataTransaction.year.value, sum = DataTransaction.sum.value, sign = sign )),
            img = mutableStateOf(img),
            color = mutableStateOf(color)
        )
    }
}