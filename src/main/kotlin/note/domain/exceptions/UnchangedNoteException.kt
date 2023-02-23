package note.domain.exceptions

import note.domain.NoteIdentifier

class UnchangedNoteException(identifier: NoteIdentifier) : Exception() {
    override val message: String = "Note with identifier $identifier is unchanged."
}
