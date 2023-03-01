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

class NoteCreator(
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
        val noteId = Identifier(note.noteId)
        val title = Title(note.title)
        val description = note.description?.let { Description(it) }
        return save(noteId, title, description)
    }

    private fun save(noteId: Identifier, title: Title, description: Description?): Note {
        val newNote = Note(
            id = noteId,
            title = title,
            description = description,
        )
        repository.search(noteId)?.let { throw AlreadyUsedIdentifierException() }
        repository.create(newNote)
        return newNote
    }
}
