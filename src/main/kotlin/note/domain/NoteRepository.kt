package note.domain

import shared.domain.Identifier

interface NoteRepository {
    fun save(note: Note)
    fun search(identifier: Identifier): Note?
    fun delete(identifier: Identifier)
}
