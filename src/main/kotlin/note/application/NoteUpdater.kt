package note.application

import note.domain.Note
import note.domain.NoteIdentifier
import note.domain.NoteIdentifierGenerator
import note.domain.NoteRepository

class NoteUpdater(
    private val repository: NoteRepository,
    private val identifierGenerator: NoteIdentifierGenerator,
) {
    fun update(identifier: NoteIdentifier, newTitle: String, newDescription: String?): Note {
        NoteDeleter(repository).delete(identifier)
        return NoteSaver(repository, identifierGenerator).save(newTitle, newDescription)
    }
}
