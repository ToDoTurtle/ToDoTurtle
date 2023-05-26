package deadline.domain

import deadline.application.DeadlinePrimitives
import shared.domain.Identifier

data class Deadline(val noteId: Identifier, val time: Time) {
    fun toPrimitives(): DeadlinePrimitives = DeadlinePrimitives(
        noteId.toPrimitive(),
        time.toPrimitive(),
    )
}
