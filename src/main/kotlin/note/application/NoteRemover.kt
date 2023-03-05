package note.application

import note.domain.Note
import note.domain.NoteRepository
import note.domain.exceptions.NonExistentNoteException
import shared.domain.Identifier
import shared.domain.exceptions.InvalidUUIDException

class NoteRemover(
    private val repository: NoteRepository,
) {

    /***
     * Deletes a Note with the given identifier and returns true if found.
     * @throws InvalidUUIDException if the note id from the primitives isn't valid
     * @throws NonExistentNoteException if the note does not exist or is not saved on the repository.
     * @see Note
     */
    fun remove(noteId: String) = remove(Identifier(noteId))
    private fun remove(noteId: Identifier) =
        NoteSearcher(repository).search(noteId)?.let { repository.remove(it.id) }
            ?: throw NonExistentNoteException(noteId)
}
