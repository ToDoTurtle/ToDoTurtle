package notification.domain

import shared.domain.Identifier

interface NotificationRepository {
    fun get(id: Identifier): Notification?
}
