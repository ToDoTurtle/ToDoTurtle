package notification.application

import deadline.domain.Time
import note.application.NoteSearcher
import note.domain.NoteRepository
import note.domain.exceptions.NonExistentNoteException
import notification.domain.Notification
import notification.domain.NotificationRepository
import notification.domain.exceptions.AlreadyConfiguredNotification
import shared.domain.Identifier
import shared.domain.exceptions.InvalidUUIDException

class NotificationCreator(
    private val repository: NotificationRepository,
    private val noteSearcher: NoteRepository
) {

    /***
     * Creates a new notification and saves it to the NotificationRepository
     * @throws InvalidUUIDException if the noted id primitive isn't in a valid UUID v4 format
     * @throws NonExistentNoteException if the note id doesn't exist on the NoteRepository
     * @throws AlreadyConfiguredNotification if a notification with the same time already exists
     * @return The notification instance that was saved inside the repository
     */
    fun create(primitives: NotificationPrimitives) = create(Identifier(primitives.noteId), Time(primitives.time))

    private fun create(noteId: Identifier, time: Time) = create(Notification(noteId, time))

    private fun create(notification: Notification): Notification {
        noteSearcher.search(notification.noteId) ?: throw NonExistentNoteException(notification.noteId)
        if (repository.search(notification.noteId).any { it == notification })
            throw AlreadyConfiguredNotification()
        repository.create(notification)
        return notification
    }
}
