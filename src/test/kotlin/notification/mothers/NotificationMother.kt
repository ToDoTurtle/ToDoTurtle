package notification.mothers

import deadline.mothers.TimeMother
import notification.application.NotificationPrimitives
import notification.domain.Notification
import shared.mothers.IdentifierMother

object NotificationMother {

    fun getNoteIdPrimitiveFrom(notification: Notification) = IdentifierMother.getPrimitiveFrom(notification.noteId)

    fun getValidNotification() = Notification(
        noteId = IdentifierMother.getValidIdentifier(),
        time = TimeMother.getValidTime()
    )

    fun getValidNotifications(): Collection<Notification> {
        val noteId = IdentifierMother.getValidIdentifier()
        val time = TimeMother.getValidTime()
        return listOf(
            Notification(noteId = noteId, time = time),
            Notification(noteId = noteId, time = TimeMother.getDifferentTime(time))
        )
    }

    fun getNoteIdFrom(notification: Notification) = notification.noteId
    fun getPrimitivesFrom(notification: Notification) = NotificationPrimitives(
        noteId = IdentifierMother.getPrimitiveFrom(notification.noteId),
        time = TimeMother.getPrimitiveFrom(notification.time)
    )

    fun getNoteIdentifierFrom(notification: Notification) = notification.noteId
}
