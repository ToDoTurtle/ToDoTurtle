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
     * @throws NonExistentNoteException if the note does not exist or is not saved on the repository.
     * @see Note
     */
    fun delete(noteId: String) = delete(Identifier(noteId))
    private fun delete(noteId: Identifier) =
        NoteSearcher(repository).search(noteId)?.let { repository.delete(it.id) }
            ?: throw NonExistentNoteException(noteId)
}
