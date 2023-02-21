package deadline.mothers

import deadline.domain.Deadline
import note.mothers.IdentifierMother

object DeadlineMother {

    fun getValidDeadline() = Deadline(note = IdentifierMother.getValidIdentifier(), time = TimeMother.getValidTime())
    fun getNoteIdentifierFromDeadline(deadline: Deadline) = deadline.note
    fun getTimeFromDeadline(deadline: Deadline) = deadline.time
    fun getWithDifferentTime(deadline: Deadline) = deadline.copy(time = TimeMother.getDifferentTime(deadline.time))
    fun getDifferentTimeFrom(deadline: Deadline) = getWithDifferentTime(deadline).time
}
