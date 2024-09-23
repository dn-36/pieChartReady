import database.PeopleDao
import database.GoalDao
import database.ProfileDao
import utils.ImageSaver
import utils.Notifications

object ServiceLocator {
    lateinit var peopleDao: PeopleDao
    lateinit var goalDao: GoalDao
    lateinit var profileDao: ProfileDao
    lateinit var imageSaver: ImageSaver
    lateinit var notifications: Notifications
}
