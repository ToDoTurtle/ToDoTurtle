package note.application

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import note.domain.NoteRepository
import note.mothers.NoteMother
import shared.mothers.IdentifierMother
import kotlin.test.assertEquals

class NoteSearcherTest {
    private lateinit var noteSearcher: NoteSearcher
    private lateinit var repository: NoteRepository

    @BeforeEach
    fun setUp() {
        repository = Mockito.mock(NoteRepository::class.java)
        noteSearcher = NoteSearcher(repository)
    }

    @Test
    fun `Nothing is returned when valid identifier doesn't exist in the repository`() {
        val identifier = IdentifierMother.getValidIdentifier()
        Mockito.`when`(repository.search(identifier)).thenReturn(null)

        val result = noteSearcher.search(identifier)

        Mockito.verify(repository, Mockito.times(1)).search(identifier)
        assertEquals(null, result)
    }

    @Test
    fun `Note is returned when identifier exists in the repository`() {
        val note = NoteMother.getValidNoteWithoutDescription()
        val id = NoteMother.getIdentifierFrom(note)
        Mockito.`when`(repository.search(id)).thenReturn(note)

        val result = noteSearcher.search(note.id)

        Mockito.verify(repository, Mockito.times(1)).search(note.id)
        assertEquals(note, result)
    }
}
