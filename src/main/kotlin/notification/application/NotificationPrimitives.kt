package notification.application

/***
 * @param noteId is the identifier of the note in primitive format.
 * @param time is the time in unix time format and in Coordinated Universal Time (UTC), if you pass
 * an incorrect time format or a different timezone (UTC +/- n) the notification will have errors.
 */
data class NotificationPrimitives(val noteId: String, val time: ULong)
