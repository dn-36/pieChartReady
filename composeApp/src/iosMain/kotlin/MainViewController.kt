import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import database.getGoalDatabase
import database.getPeopleDatabase
import database.getProfileDatabase

fun MainViewController() = ComposeUIViewController {
    val dao = remember { getPeopleDatabase().peopleDao() }
    val daoGoal = remember { getGoalDatabase().goalDao() }
    val daoProfile = remember { getProfileDatabase().profileDao() }
    ServiceLocator.peopleDao = dao
    ServiceLocator.goalDao = daoGoal
    ServiceLocator.profileDao = daoProfile
    ServiceLocator.imageSaver = ImageSaverImpl()
    ServiceLocator.notifications = RequestNotificationPermission()

    App()
}