package notification.mothers

import notification.domain.Notification
import shared.mothers.IdentifierMother

object NotificationMother {

    fun getNoteIdPrimitiveFrom(notification: Notification) = IdentifierMother.getPrimitiveFrom(notification.noteId)

    fun getValidNotification() = Notification(noteId = IdentifierMother.getValidIdentifier())

    fun getValidNotifications(): Collection<Notification> {
        val noteId = IdentifierMother.getValidIdentifier()
        return listOf(
            Notification(noteId = noteId),
            Notification(noteId = noteId)
        )
    }

    fun getNoteIdFrom(notification: Notification) = notification.noteId
}
