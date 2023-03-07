package notification.domain

import deadline.domain.Time
import shared.domain.Identifier

data class Notification(val noteId: Identifier, val time: Time)
