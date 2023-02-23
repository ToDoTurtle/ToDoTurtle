package note.application

import note.domain.Note
import note.domain.NotePrimitives
import note.domain.NoteRepository
import note.domain.exceptions.NonExistentNoteException
import note.domain.exceptions.UnchangedNoteException
import shared.domain.Identifier

class NoteUpdater(
    private val repository: NoteRepository,
) {
    fun update(oldNoteIdentifier: String, newNote: NotePrimitives): Note {
        val oldNoteId = Identifier(oldNoteIdentifier)
        val oldNote = repository.search(oldNoteId) ?: throw NonExistentNoteException(oldNoteId)
        val oldNotePrimitives = oldNote.toPrimitives()
        if (newNote.hasSameContent(oldNotePrimitives)) throw UnchangedNoteException(oldNoteId)
        return update(oldNoteId = oldNoteId, newNote = newNote)
    }

    private fun update(oldNoteId: Identifier, newNote: NotePrimitives): Note {
        val result = NoteSaver(repository).save(newNote)
        repository.delete(oldNoteId)
        return result
    }
}
