package note.application

import note.domain.NoteRepository
import note.mothers.NoteMother
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import shared.domain.IdentifierGenerator
import shared.mothers.IdentifierMother
import kotlin.test.assertEquals

class NoteSearcherTest {
    private lateinit var generator: IdentifierGenerator
    private lateinit var noteSearcher: NoteSearcher
    private lateinit var repository: NoteRepository

    @BeforeEach
    fun setUp() {
        repository = Mockito.mock(NoteRepository::class.java)
        generator = Mockito.mock(IdentifierGenerator::class.java)
        noteSearcher = NoteSearcher(repository)
    }

    @Test
    fun `Nothing is returned when valid identifier doesn't exist in the repository`() {
        // Initialize
        val identifier = IdentifierMother.getValidIdentifier()
        Mockito.`when`(repository.search(identifier)).thenReturn(null)
        // Execute
        val result = noteSearcher.search(identifier)
        // Assert
        Mockito.verify(repository, Mockito.times(1)).search(identifier)
        assertEquals(null, result)
    }

    @Test
    fun `Note is returned when identifier exists in the repository`() {
        // Initialize
        val note = NoteMother.getValidNoteWithoutDescription()
        Mockito.`when`(repository.search(note.id)).thenReturn(note)
        // Execute
        val result = noteSearcher.search(note.id)
        // Assert
        Mockito.verify(repository, Mockito.times(1)).search(note.id)
        assertEquals(note, result)
    }
}
