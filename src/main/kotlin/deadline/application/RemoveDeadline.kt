package deadline.application

import deadline.domain.Deadline
import deadline.domain.DeadlineRepository
import note.domain.NoteIdentifier

class NonExistentDeadlineException : Throwable()

class RemoveDeadline(private val deadlineRepository: DeadlineRepository) {

    fun remove(noteId: NoteIdentifier): Deadline {
        val deadline = deadlineRepository.get(noteId) ?: throw NonExistentDeadlineException()
        deadlineRepository.delete(deadline)
        return deadline
    }
}
