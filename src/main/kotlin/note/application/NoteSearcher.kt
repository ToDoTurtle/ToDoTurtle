package note.application

import note.domain.Note
import note.domain.NoteIdentifier
import note.domain.NoteRepository

class NoteSearcher(
    private val repository: NoteRepository,
) {

    /***
     * Searches a Note with the given identifier and returns it if found.
     * @see Note
     * @return null or a note instance if it exists
     */
    fun search(identifier: NoteIdentifier) = repository.search(identifier)

}