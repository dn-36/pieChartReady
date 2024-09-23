package org.example.project.presentation.wallets_feature.list_wallets_feature.viewmodel.intents

import androidx.compose.ui.graphics.Color
import org.example.project.core.StaticDate
import org.example.project.presentation.wallets_feature.list_wallets_feature.viewmodel.ListWalletsViewModel

object SetListWalletIntent {
    fun execute(){
        if (ListWalletsViewModel(StaticDate).date.isUsedListWallet.value) {
            ListWalletsViewModel(StaticDate).date.isUsedListWallet.value = false

            // Проверка, что список не пустой
            if (ListWalletsViewModel(StaticDate).date.listWallets.isNotEmpty()) {
                // Создаем новый список цветов с такой же длиной, как и количество кошельков
                var newList = MutableList(ListWalletsViewModel(StaticDate).date.listWallets.size) {
                    Color(0xFFC0C4C3)
                }

                // Проверка, что текущий индекс допустим
                val currentIndex = ListWalletsViewModel(StaticDate).date.indexWalletNow.value
                if (currentIndex >= 0 && currentIndex < newList.size) {
                    // Устанавливаем цвет для текущего кошелька
                    newList[currentIndex] = Color(0xFF027B5B)
                }

                // Обновляем состояние ViewModel с новым списком цветов и кошельков
                ListWalletsViewModel.listWalletsState = ListWalletsViewModel.listWalletsState.copy(
                    listColor = newList,
                    listWallets = ListWalletsViewModel(StaticDate).date.listWallets
                )
            }
        }
    }
}