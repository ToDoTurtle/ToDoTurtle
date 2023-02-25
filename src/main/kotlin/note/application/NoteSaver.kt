package note.application

import note.domain.Description
import note.domain.Note
import note.domain.NoteRepository
import note.domain.Title
import shared.domain.IdentifierGenerator

class NoteSaver(
    private val repository: NoteRepository,
    private val identifierGenerator: IdentifierGenerator,
) {

    /***
     * Creates a Note with the identifier generated by the identifierGenerator instance and
     * saves it to the given repository.
     * @see Note
     * @see IdentifierGenerator
     * @return The saved note instance
     */
    fun save(title: String, description: String?): Note {
        val note = Note(
            id = identifierGenerator.generate(),
            title = Title(title),
            description = description?.let { Description(it) },
        )
        repository.save(note)
        return note
    }
}