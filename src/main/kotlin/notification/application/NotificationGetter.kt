package notification.application

import notification.domain.Notification
import notification.domain.NotificationRepository
import shared.domain.Identifier
import java.util.Collections

class NotificationGetter(
    private val repository: NotificationRepository,
) {
    /***
     * Looks for a Notification with the given identifier in the repository.
     * @see Notification
     * @return The Notification instance if it exists, null otherwise.
     */
    fun get(id: String) = repository.get(Identifier(id))
}
