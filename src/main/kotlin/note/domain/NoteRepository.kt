package note.domain

interface NoteRepository {
    fun save(note: Note)
    fun search(identifier: NoteIdentifier): Note?
    fun delete(identifier: NoteIdentifier)
}
