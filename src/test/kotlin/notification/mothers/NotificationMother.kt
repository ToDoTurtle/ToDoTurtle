package notification.mothers

import notification.domain.Notification
import shared.domain.Identifier
import shared.mothers.IdentifierMother

object NotificationMother {

    fun getValidNotification() = Notification(
        id = IdentifierMother.getValidIdentifier(),
        noteId = IdentifierMother.getValidIdentifier()
    )

    fun getNotificationFromNoteId(noteId: Identifier) = Notification(
        id = IdentifierMother.getValidIdentifier(),
        noteId = noteId
    )

    fun getNotificationsFromNoteId(noteId: Identifier) = listOf(
        Notification(
            id = IdentifierMother.getValidIdentifier(),
            noteId = noteId
        ),
        Notification(
            id = IdentifierMother.getValidIdentifier(),
            noteId = noteId
        )
    )

    fun getIdentifierPrimitiveFrom(notification: Notification) = IdentifierMother.getPrimitiveFrom(notification.id)
}
