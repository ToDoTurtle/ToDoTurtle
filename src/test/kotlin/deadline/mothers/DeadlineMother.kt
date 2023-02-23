package deadline.mothers

import deadline.application.DeadlinePrimitives
import deadline.domain.Deadline
import shared.mothers.IdentifierMother

object DeadlineMother {

    fun getValidDeadline() = Deadline(noteId = IdentifierMother.getValidIdentifier(), time = TimeMother.getValidTime())
    fun getNoteIdentifierFromDeadline(deadline: Deadline) = deadline.noteId
    fun getTimeFromDeadline(deadline: Deadline) = deadline.time
    fun getWithDifferentTime(deadline: Deadline) = deadline.copy(time = TimeMother.getDifferentTime(deadline.time))
    fun getDifferentTimeFrom(deadline: Deadline) = getWithDifferentTime(deadline).time
    fun getPrimitivesFrom(currentDeadline: Deadline) =
        DeadlinePrimitives(currentDeadline.noteId.id, currentDeadline.time.time)
}
