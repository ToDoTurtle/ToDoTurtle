package note.application

import note.domain.Note
import note.domain.NoteRepository
import shared.domain.Identifier

class NoteSearcher(
    private val repository: NoteRepository,
) {

    /***
     * Searches a Note with the given identifier and returns it if found.
     * @see Note
     * @return null or a note instance if it exists
     */
    fun search(identifier: String) = repository.search(Identifier(identifier))
    internal fun search(identifier: Identifier) = repository.search(identifier)
}
