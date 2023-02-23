package note.domain

import shared.domain.Identifier

interface NoteRepository {
    fun save(note: Note)
    fun search(noteId: Identifier): Note?
    fun delete(noteId: Identifier)
}
