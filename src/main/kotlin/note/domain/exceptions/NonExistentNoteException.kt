package note.domain.exceptions

import shared.domain.Identifier

class NonExistentNoteException(identifier: Identifier) : Exception() {
    override val message: String = "Note with identifier $identifier does not exist or is not saved on the repository."
}
