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
        val title = oldNote.toPrimitives().title
        val desc = oldNote.toPrimitives().description
        if (newNote.title == title && newNote.description == desc) throw UnchangedNoteException(oldNoteId)
        repository.delete(oldNoteId)
        return NoteSaver(repository).save(newNote)
    }
}
