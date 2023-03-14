package notification.domain

import deadline.domain.Time
import shared.domain.Identifier

interface NotificationRepository {
    fun search(id: Identifier): Collection<Notification>
    fun create(notification: Notification)
    fun remove(notification: Notification)
}
