package note.domain.exceptions

import shared.domain.Identifier

class NonExistentNoteException(noteId: Identifier) : Exception() {
    override val message: String = "Note with identifier $noteId does not exist or is not saved on the repository."
}
