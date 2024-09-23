package presentation.profile_feature.viewmodel.intent

import ServiceLocator
import database.Profile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.example.project.core.StaticDate
import org.example.project.presentation.menu_feature.screen.MenuScreen
import org.example.project.presentation.profile_feature.viewmodel.ProfileViewModel
import piechart.composeapp.generated.resources.Res
import piechart.composeapp.generated.resources.man_ava

object HomeIntent {
    fun execute(){
        StaticDate.navigator.push(MenuScreen)
        var name = ProfileViewModel(StaticDate).date.name
        var notifications  = ProfileViewModel(StaticDate).date.notifications
        var sounds  = ProfileViewModel(StaticDate).date.notifications
        var gender = if(ProfileViewModel(StaticDate).date.gender  == Res.drawable.man_ava) "man" else "woman"
        CoroutineScope(Dispatchers.IO).launch {
            ServiceLocator.profileDao.update(Profile(id = 1, name = name, gender = gender,notifications = notifications,sounds = sounds))
        }
    }
}