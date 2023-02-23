package deadline.application

import deadline.domain.Deadline
import deadline.domain.DeadlineRepository
import deadline.domain.Time
import deadline.domain.exceptions.AlreadyConfiguredDeadline
import note.domain.NoteIdentifier

class CreateDeadline(private val repository: DeadlineRepository) {

    /***
     * Creates a new deadline and saves it to the DeadlineRepository
     * @param unixTime is the time in unix time format and in Coordinated Universal Time (UTC), if you pass
     * an incorrect time format or a different timezone (UTC +/- n) the deadline will be saved with time errors.
     * @param noteId is the identifier of the note in primitive format.
     * @throws AlreadyConfiguredDeadline if a new deadline already exists
     * @return The deadline instance that was saved inside the repository
     */
    fun create(noteId: String, unixTime: ULong) = create(NoteIdentifier(noteId), Time(unixTime))

    internal fun create(id: NoteIdentifier, time: Time): Deadline {
        val deadline = repository.get(id)?.let { throw AlreadyConfiguredDeadline() } ?: Deadline(id, time)
        repository.save(deadline)
        return deadline
    }
}
