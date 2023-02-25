package note.application

import note.domain.Note
import note.domain.NoteRepository
import note.domain.exceptions.NonExistentNoteException
import shared.domain.Identifier

class NoteDeleter(
    private val repository: NoteRepository,
) {

    /***
     * Deletes a Note with the given identifier and returns true if found.
     * @see Note
     * @return Unit if the note was deleted or throws an exception if it does not exist.
     * @throws NonExistentNoteException if the note does not exist or is not saved on the repository.
     */
    fun delete(identifier: Identifier) =
        NoteSearcher(repository).search(identifier)?.let { repository.delete(it.id) }
            ?: throw NonExistentNoteException(identifier)
}
