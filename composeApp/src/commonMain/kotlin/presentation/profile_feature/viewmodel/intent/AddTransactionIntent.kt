package presentation.profile_feature.viewmodel.intent

import org.example.project.core.StaticDate
import org.example.project.presentation.add_transaction.choosing_category_feature.screen.ChoosingCategoryScreen

object AddTransactionIntent{
    fun execute(){
        StaticDate.navigator.push(ChoosingCategoryScreen)
    }
}