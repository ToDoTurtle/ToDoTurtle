package deadline.domain

import note.domain.NoteIdentifier

interface DeadlineRepository {
    fun save(deadline: Deadline)
    fun get(noteIdentifier: NoteIdentifier): Deadline?
}
