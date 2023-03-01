package notification.application

import note.domain.Note
import notification.domain.Notification
import notification.domain.NotificationRepository
import shared.domain.Identifier

class NotificationSearcher(
    private val repository: NotificationRepository,
) {
    /***
     * Looks for all Notifications with the given Note identifier in the repository.
     * @see Notification
     * @see Note
     * @return A collection of Notification instances (empty if none found)
     */
    fun search(noteId: String) = search(Identifier(noteId))

    private fun search(noteId: Identifier) = repository.search(noteId)
}
