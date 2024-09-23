package utils

interface Notifications {
    fun requestNotificationPermission(onPermissionGranted: () -> Unit)//(onPermissionGranted: () -> Unit)
    fun scheduleLocalNotification()
    fun toggleNotifications()
   // fun checkNotificationAuthorization(onAuthorized: () -> Unit)
}