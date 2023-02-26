package note.application

import note.domain.Note
import note.domain.NotePrimitives
import note.domain.NoteRepository
import note.domain.exceptions.IllegalTitleException
import note.domain.exceptions.NonExistentNoteException
import note.domain.exceptions.UnchangedNoteException
import shared.domain.Identifier
import shared.domain.exceptions.AlreadyUsedIdentifierException
import shared.domain.exceptions.InvalidUUIDException

class NoteUpdater(
    private val repository: NoteRepository,
) {
    /***
     * Updates a Note with the given identifier with the newNote primitives.
     * @throws InvalidUUIDException if the new note id from the primitives isn't valid
     * @throws AlreadyUsedIdentifierException if the id of the new note is already used on the repository
     * @throws NonExistentNoteException if the old note identifier doesn't exist on the repository
     * @throws UnchangedNoteException if the new note content is the same as the old one
     * (doesn't compare the identifier, only the content)
     * @throws IllegalTitleException if the new title isn't valid
     * @return The new saved instance of Note
     */
    fun update(oldNoteIdentifier: String, newNote: NotePrimitives): Note {
        val oldNoteId = Identifier(oldNoteIdentifier)
        assertUpdateConditions(oldNoteId, newNote)
        return update(oldNoteId = oldNoteId, newNote = newNote)
    }

    private fun assertUpdateConditions(oldNoteId: Identifier, newNote: NotePrimitives) {
        val oldNote = repository.search(oldNoteId) ?: throw NonExistentNoteException(oldNoteId)
        if (newNote.hasSameContent(oldNote.toPrimitives())) throw UnchangedNoteException(oldNoteId)
    }

    private fun update(oldNoteId: Identifier, newNote: NotePrimitives): Note {
        val result = NoteSaver(repository).save(newNote)
        repository.delete(oldNoteId)
        return result
    }
}
