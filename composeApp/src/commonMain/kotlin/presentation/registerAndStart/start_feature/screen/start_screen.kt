package org.example.project.presentation.registerAndStart.start_feature.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.project.core.StaticDate
import org.example.project.presentation.registerAndStart.start_feature.viewmodel.StartIntents
import org.example.project.presentation.registerAndStart.start_feature.viewmodel.StartViewModel
import org.jetbrains.compose.resources.painterResource
import piechart.composeapp.generated.resources.finance_assistant
import piechart.composeapp.generated.resources.start_button
import piechart.composeapp.generated.resources.Res
import piechart.composeapp.generated.resources.start_avatar
import androidx.compose.material.Text
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex


object StartScreen:Screen {
    val vm = StartViewModel(StaticDate)

    @Composable
    override fun Content() {
        StaticDate.navigator = LocalNavigator.currentOrThrow

        Box(modifier = Modifier.fillMaxSize()) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)
                        .background(Color.White), contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(Res.drawable.start_avatar),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(0.8f).fillMaxHeight(0.7f)
                    )
                }
                Box(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight()
                        .background(Color(0xFF027B5B)), contentAlignment = Alignment.Center
                ) {
                    Image(painter = painterResource(Res.drawable.start_button),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(0.5f).fillMaxHeight(0.07f)
                            .clickable(
                                indication = null, // Отключение эффекта затемнения
                                interactionSource = remember { MutableInteractionSource() })
                            { vm.processIntent(StartIntents.Start) })
                }
            }
            Image(
                painter = painterResource(Res.drawable.finance_assistant),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(0.7f).fillMaxHeight(0.1f).align(Alignment.Center)
            )

        }
    }
}
