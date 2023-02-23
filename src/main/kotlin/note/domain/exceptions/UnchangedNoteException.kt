package note.domain.exceptions

import shared.domain.Identifier

class UnchangedNoteException(noteId: Identifier) : Exception() {
    override val message: String = "Note with id: $noteId has the same content and therefore will not be updated"
}
