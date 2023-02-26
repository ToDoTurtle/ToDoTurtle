package note.application

import note.domain.Description
import note.domain.Note
import note.domain.NotePrimitives
import note.domain.NoteRepository
import note.domain.Title
import note.domain.exceptions.IllegalTitleException
import shared.domain.Identifier
import shared.domain.exceptions.AlreadyUsedIdentifierException
import shared.domain.exceptions.InvalidUUIDException

class NoteSaver(
    private val repository: NoteRepository,
) {

    /***
     * Creates a Note and saves it to the given repository.
     * @throws InvalidUUIDException if the note id from the primitives isn't valid
     * @throws AlreadyUsedIdentifierException if the note id is already used on the repository
     * @throws IllegalTitleException if the title isn't valid
     * @see NotePrimitives
     * @see Note
     * @return The saved note instance
     */
    fun save(note: NotePrimitives): Note {
        val noteIdentifier = Identifier(note.noteId)
        val newNote = Note(
            id = noteIdentifier,
            title = Title(note.title),
            description = note.description?.let { Description(it) },
        )
        repository.search(noteIdentifier)?.let { throw AlreadyUsedIdentifierException() }
        repository.save(newNote)
        return newNote
    }
}
