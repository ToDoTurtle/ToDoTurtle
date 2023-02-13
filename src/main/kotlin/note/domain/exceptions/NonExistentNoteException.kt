package note.domain.exceptions

import note.domain.NoteIdentifier

class NonExistentNoteException(identifier: NoteIdentifier) : Exception() {
    override val message: String = "Note with identifier $identifier does not exist or is not saved on the repository."
}
