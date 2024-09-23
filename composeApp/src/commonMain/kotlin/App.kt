import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import database.PeopleDao
import database.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.example.project.core.StaticDate
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import piechart.composeapp.generated.resources.Res
import piechart.composeapp.generated.resources.clicked_cub
import piechart.composeapp.generated.resources.compose_multiplatform
import piechart.composeapp.generated.resources.man_ava
import piechart.composeapp.generated.resources.woman_ava
import presentation.settings_feature.viewmodel.SettingsViewModel

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {

        LaunchedEffect(Unit) {

            val people = withContext(Dispatchers.IO) {
                ServiceLocator.peopleDao.getAllPeople()
            }
            if (people.isNotEmpty()) {
                people.forEach { it ->
                    StaticDate.listWallets.add(it.wallet)
                }
                println("Fetched people: $people")
            }
            val goal = withContext(Dispatchers.IO) {
                ServiceLocator.goalDao.getAllGoal()
            }
            if (goal.isNotEmpty()) {
                goal.forEach { it ->
                    StaticDate.listGoals.add(it.goal)
                }
                println("Fetched people: $people")
            }

            val profile = withContext(Dispatchers.IO) {
                ServiceLocator.profileDao.getAllProfile()
            }
            if (profile.isNotEmpty()) {
                profile.forEach { it ->
                    StaticDate.name = it.name
                    StaticDate.notifications = it.notifications
                    StaticDate.sounds = it.sounds
                    if(it.gender == "man"){
                    StaticDate.gender = Res.drawable.man_ava}
                    else if(it.gender == "woman"){
                        StaticDate.gender = Res.drawable.woman_ava
                    }
                }
                println("Fetched people: $profile")
            }

        }

        navigator()


    }
}