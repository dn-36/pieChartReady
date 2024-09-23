import kotlinx.cinterop.ObjCClass
import platform.Foundation.NSDictionary
import platform.Foundation.setValue
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationDelegateProtocol
import platform.UIKit.UIResponder
import platform.UserNotifications.*
import platform.darwin.NSObject
import utils.Notifications

class RequestNotificationPermission : Notifications {
    private var areNotificationsEnabled = false

    override fun requestNotificationPermission(onPermissionGranted: () -> Unit) {
        UNUserNotificationCenter.currentNotificationCenter().requestAuthorizationWithOptions(
            UNAuthorizationOptionAlert or UNAuthorizationOptionSound or UNAuthorizationOptionBadge
        ) { granted, error ->
            if (granted) {
                println("Разрешение получено")
                onPermissionGranted() // Вызываем функцию планирования уведомления только после получения разрешения
            } else {
                println("Разрешение отклонено")
            }
        }
    }

    override fun scheduleLocalNotification() {
        if (!areNotificationsEnabled) {
            println("Уведомления отключены")
            return
        }

        val content = UNMutableNotificationContent()
        content.setValue("Напоминание", forKey = "title")
        content.setValue("Пора проверить свои финансы", forKey = "body")

        content.setValue(UNNotificationSound.defaultSound(), forKey = "sound")


        val trigger = UNTimeIntervalNotificationTrigger.triggerWithTimeInterval(14400.0, repeats = true)
        val request = UNNotificationRequest.requestWithIdentifier("testNotification", content, trigger)
        UNUserNotificationCenter.currentNotificationCenter().addNotificationRequest(request) { error ->
            if (error != null) {
                println("Ошибка при планировании уведомления: $error")
            } else {
                println("Уведомление успешно запланировано")
            }
        }
    }

    override fun toggleNotifications() {
        areNotificationsEnabled = !areNotificationsEnabled
        if (areNotificationsEnabled) {
            println("Уведомления включены")
            scheduleLocalNotification() // Запускаем уведомления, если они активированы
        } else {
            cancelNotifications() // Отключаем уведомления
        }
    }

    private fun cancelNotifications() {
        UNUserNotificationCenter.currentNotificationCenter().removeAllPendingNotificationRequests()
        println("Все уведомления отменены")
    }
}

/*class RequestNotificationPermission: Notifications {

    private val notificationDelegate = NotificationDelegate()

    init {
        // Установка делегата
        UNUserNotificationCenter.currentNotificationCenter().delegate = notificationDelegate
    }

    override fun requestNotificationPermission(onPermissionGranted: () -> Unit) {
        UNUserNotificationCenter.currentNotificationCenter().requestAuthorizationWithOptions(
            UNAuthorizationOptionAlert or UNAuthorizationOptionSound or UNAuthorizationOptionBadge
        ) { granted, error ->
            if (granted) {
                println("Разрешение получено")
                onPermissionGranted() // Вызываем функцию планирования уведомления только после получения разрешения
            } else {
                println("Разрешение отклонено")
            }
        }
    }

    override fun scheduleLocalNotification() {
        val content = UNMutableNotificationContent()
        content.setValue("Напоминание", forKey = "title")
        content.setValue("Пора проверить свои финансы", forKey = "body")
        content.setValue(UNNotificationSound.defaultSound(), forKey = "sound")

        val trigger = UNTimeIntervalNotificationTrigger.triggerWithTimeInterval(60.0, repeats = true)
        val request = UNNotificationRequest.requestWithIdentifier("testNotification", content, trigger)
        UNUserNotificationCenter.currentNotificationCenter().addNotificationRequest(request) { error ->
            if (error != null) {
                println("Ошибка при планировании уведомления: $error")
            } else {
                println("Уведомление успешно запланировано")
            }
        }
    }
}

// Создаем отдельный класс для делегата
class NotificationDelegate: NSObject(), UNUserNotificationCenterDelegateProtocol {

    // Обрабатываем уведомления при активном приложении
    override fun userNotificationCenter(
        center: UNUserNotificationCenter,
        willPresentNotification: UNNotification,
        withCompletionHandler: (UNNotificationPresentationOptions) -> Unit
    ) {
        println("Уведомление получено при активном приложении")
        // Показываем уведомление даже если приложение открыто
        withCompletionHandler(UNNotificationPresentationOptionAlert or UNNotificationPresentationOptionSound)
    }

    override fun `class`(): ObjCClass? {
        return null
    }
}*/
