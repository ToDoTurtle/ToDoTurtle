package deadline.application

import deadline.domain.Deadline
import deadline.domain.DeadlineRepository
import deadline.domain.Time
import deadline.domain.exceptions.AlreadyConfiguredDeadline
import shared.domain.Identifier
import shared.domain.exceptions.InvalidUUIDException

class CreateDeadline(private val repository: DeadlineRepository) {

    /***
     * Creates a new deadline and saves it to the DeadlineRepository
     * @throws AlreadyConfiguredDeadline if a new deadline already exists
     * @throws InvalidUUIDException if the noted id primitive isn't in a valid UUID v4 format
     * @return The deadline instance that was saved inside the repository
     */
    fun create(primitives: DeadlinePrimitives) = create(Identifier(primitives.noteIdentifier), Time(primitives.time))

    private fun create(noteId: Identifier, time: Time): Deadline {
        val deadline = repository.get(noteId)?.let { throw AlreadyConfiguredDeadline() } ?: Deadline(noteId, time)
        repository.save(deadline)
        return deadline
    }
}
