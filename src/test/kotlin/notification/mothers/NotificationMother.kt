package notification.mothers

import notification.domain.Notification
import shared.domain.Identifier
import shared.mothers.IdentifierMother

object NotificationMother {

    fun getValidNotification() = Notification(id = IdentifierMother.getValidIdentifier())
    fun getIdentifierPrimitiveFrom(notification: Notification) = IdentifierMother.getPrimitiveFrom(notification.id)
}
