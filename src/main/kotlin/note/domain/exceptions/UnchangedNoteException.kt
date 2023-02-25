package note.domain.exceptions

import shared.domain.Identifier

class UnchangedNoteException(identifier: Identifier) : Exception() {
    override val message: String = "Note with identifier $identifier is unchanged."
}
