package note.domain

import shared.domain.Identifier

interface NoteRepository {
    fun create(note: Note)
    fun search(noteId: Identifier): Note?
    fun remove(noteId: Identifier)
}
