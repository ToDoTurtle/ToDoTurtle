package deadline.application

import deadline.domain.Deadline
import deadline.domain.DeadlineRepository
import shared.domain.Identifier

class NonExistentDeadlineException : Throwable()

class RemoveDeadline(private val deadlineRepository: DeadlineRepository) {

    fun remove(noteId: Identifier): Deadline {
        val deadline = deadlineRepository.search(noteId) ?: throw NonExistentDeadlineException()
        deadlineRepository.delete(deadline)
        return deadline
    }
}
