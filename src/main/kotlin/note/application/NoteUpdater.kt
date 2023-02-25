package note.application

import note.domain.Note
import note.domain.NotePrimitives
import note.domain.NoteRepository
import note.domain.exceptions.NonExistentNoteException
import note.domain.exceptions.UnchangedNoteException
import shared.domain.Identifier
import shared.domain.IdentifierGenerator

class NoteUpdater(
    private val repository: NoteRepository,
    private val identifierGenerator: IdentifierGenerator,
) {
    fun update(identifier: Identifier, newTitle: String, newDescription: String?): Note {
        val note = repository.search(identifier) ?: throw NonExistentNoteException(identifier)
        if (unchangedNote(note, newTitle, newDescription)) throw UnchangedNoteException(identifier)
        repository.delete(identifier)
        return NoteSaver(repository, identifierGenerator).save(newTitle, newDescription)
    }

    private fun unchangedNote(
        note: Note,
        newTitle: String,
        newDescription: String?,
    ): Boolean {
        val newNotePrimitives = NotePrimitives(newTitle, newDescription)
        return newNotePrimitives == note.toPrimitives()
    }
}
