package notification.application

import notification.domain.Notification
import notification.domain.NotificationRepository
import shared.domain.Identifier
import note.domain.Note

class NotificationGetter(
    private val repository: NotificationRepository,
) {
    /***
     * Looks for a Notification with the given identifier in the repository.
     * @see Notification
     * @return The Notification instance if it exists, null otherwise.
     */
    fun get(id: String) = repository.get(Identifier(id))

    /***
     * Looks for all Notifications with the given note identifier in the repository.
     * @see Notification
     * @see Note
     * @return A collection of Notification instances if they exist, an empty collection otherwise.
     */
    fun getAll(noteId: String) = repository.getAll(Identifier(noteId))
}
