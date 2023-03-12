package notification.application

import deadline.domain.Time
import note.application.NoteSearcher
import note.domain.Note
import note.domain.NoteRepository
import note.domain.exceptions.NonExistentNoteException
import notification.domain.Notification
import notification.domain.NotificationRepository
import notification.domain.exceptions.NonExistentNotificationException
import shared.domain.Identifier
import shared.domain.exceptions.InvalidUUIDException

class NotificationRemover(
    private val notificationRepository: NotificationRepository,
    private val noteRepository: NoteRepository
) {
    /***
     * Deletes a Notification with the given time and note identifier.
     * @throws InvalidUUIDException if the note id from the primitives isn't valid
     * @throws NonExistentNoteException if the note does not exist or is not saved on the repository.
     * @throws NonExistentNotificationException if a notification with the given time does not exist for the given note.
     * @see Note
     * @see Notification
     */
    fun remove(notificationPrimitives: NotificationPrimitives) =
        remove(Identifier(notificationPrimitives.noteId), Time(notificationPrimitives.time))

    private fun remove(noteId: Identifier, time: Time) {
        noteRepository.search(noteId) ?: throw NonExistentNoteException(noteId)
        notificationRepository.search(noteId).find { it.time == time }
            ?: throw NonExistentNotificationException()
        notificationRepository.remove(Notification(noteId, time))
    }
}
