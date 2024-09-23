package presentation.settings_feature.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.delay
import org.example.project.core.StaticDate
import org.example.project.presentation.wallets_feature.detail_wallet_feature.viewmodel.DetailWalletIntents
import org.example.project.presentation.wallets_feature.detail_wallet_feature.viewmodel.DetailWalletViewModel
import org.example.project.presentation.wish_list.already_accumulated_feature.viewmodel.AlreadyAccumulatedViewModel
import org.jetbrains.compose.resources.painterResource
import piechart.composeapp.generated.resources.Res
import piechart.composeapp.generated.resources.back_button
import piechart.composeapp.generated.resources.empty_cub
import presentation.settings_feature.viewmodel.SettingsIntents
import presentation.settings_feature.viewmodel.SettingsViewModel

object SettingsScreen: Screen {
    var vm = SettingsViewModel(StaticDate)

    @Composable
    override fun Content() {
        val isVisible by remember { mutableStateOf(Animatable(0f)) }

        vm.processIntent(SettingsIntents.SetScreen)
        Box(modifier = Modifier.fillMaxSize()) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.13f)
                        .background(Color(0xFF027B5B))
                ) {
                    Image(
                        painter = painterResource(Res.drawable.back_button),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 25.dp, start = 20.dp)
                            .size(20.dp)
                            .align(Alignment.TopStart)
                            .clickable(
                                indication = null, // Отключение эффекта затемнения
                                interactionSource = remember { MutableInteractionSource() })
                            { vm.processIntent(SettingsIntents.Back) }
                    )
                    Text(
                        text = "Settings",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Box(
                    modifier = Modifier.fillMaxSize()
                        .background(Color.White), contentAlignment = Alignment.Center
                ) {
                    Column(modifier = Modifier.align(Alignment.Center)) {
                        Row(modifier = Modifier.fillMaxWidth(0.8f), horizontalArrangement = Arrangement.SpaceBetween
                        , verticalAlignment = Alignment.CenterVertically){
                             Text("Notifications", color = Color.Black, fontSize = 20.sp)
                                Image(painter = painterResource(SettingsViewModel.settingsState.imgNotifications.value),contentDescription = null,
                                    modifier = Modifier.width(100.dp).height(40.dp).clickable(
                                        indication = null, // Отключение эффекта затемнения
                                        interactionSource = remember { MutableInteractionSource() })
                                    { vm.processIntent(SettingsIntents.Notifications) })
                        }
                        Spacer(modifier = Modifier.height(30.dp))

                    }
                }
            }
            if (SettingsViewModel.settingsState.toast.value) {
                LaunchedEffect(key1 = true) {
                    delay(0)
                    isVisible.animateTo(
                        targetValue = 1f
                    )

                    delay(2000) // видимость на 1 секунду
                    isVisible.animateTo(
                        targetValue = 0f,
                        animationSpec = tween(
                            durationMillis = 1000,
                            easing = LinearEasing
                        ) // плавное исчезновение

                    )
                    SettingsViewModel.settingsState.toast.value = false
                }

            }
            Box(
                modifier = Modifier
                    .padding(bottom = 50.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .alpha(isVisible.value)
                    .align(Alignment.BottomCenter)
                    .background(Color.White)
                    .width(310.dp).height(80.dp)
                    .border(
                        width = 2.dp,
                        color = Color(0xFF027B5B),
                        shape = RoundedCornerShape(15.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "You have enabled notifications and now \nyou will receive reminders every 4 hours", fontSize = 15.sp,
                    color = Color.Black, textAlign = TextAlign.Center
                )
            }
        }
    }
}