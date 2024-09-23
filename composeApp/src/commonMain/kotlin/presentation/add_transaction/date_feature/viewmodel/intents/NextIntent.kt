package org.example.project.presentation.add_transaction.date_feature.viewmodel.intents

import androidx.compose.runtime.mutableStateOf
import org.example.project.core.StaticDate
import org.example.project.presentation.add_transaction.utils.DataTransaction
import org.example.project.presentation.add_transaction.date_feature.viewmodel.DateViewModel
import org.example.project.presentation.add_transaction.name_feature.screen.NameScreen

object NextIntent {
    fun execute(){
        DataTransaction.month.value = DateViewModel.dateState.textMonth.value
        DataTransaction.day.value = DateViewModel.dateState.textDay.value
        DataTransaction.year.value = DateViewModel.dateState.textYear.value


        println("2")
        println("2")
        println("2")
        println("2")
        println("${DataTransaction.day.value}")
        println("2")
        println("2")
        println("2")
        StaticDate.navigator.push(NameScreen)

        DateViewModel.dateState = DateViewModel.dateState.copy(
            textMonth = mutableStateOf("September"),
            textYear = mutableStateOf("2024"),
            textDay = mutableStateOf("1")
        )
    }
}