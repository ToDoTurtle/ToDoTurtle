package deadline.application

import deadline.domain.Deadline
import deadline.domain.DeadlineRepository
import deadline.domain.Time
import note.domain.NoteIdentifier

class AlreadyConfiguredDeadline : Throwable()

class CreateDeadline(private val repository: DeadlineRepository) {

    /***
     * Creates a new deadline and saves it to the DeadlineRepository
     * @throws AlreadyConfiguredDeadline if a new deadline already exists
     * @return The deadline instance that was saved inside the repository
     */
    fun create(id: NoteIdentifier, time: Time): Deadline {
        val deadline = repository.get(id)?.let { throw AlreadyConfiguredDeadline() } ?: Deadline(id, time)
        repository.save(deadline)
        return deadline
    }
}
