package notification.domain

import shared.domain.Identifier

interface NotificationRepository {
    fun search(id: Identifier): Collection<Notification>
    fun create(notification: Notification)
}
