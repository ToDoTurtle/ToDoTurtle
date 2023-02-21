package note.application

import note.domain.Note
import note.domain.NoteIdentifierGenerator
import note.domain.NoteRepository
import note.domain.exceptions.IllegalTitleException
import note.mothers.NoteMother
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import kotlin.test.assertEquals

class NoteSaverTest {

    private lateinit var noteSaver: NoteSaver
    private lateinit var generator: NoteIdentifierGenerator
    private lateinit var repository: NoteRepository

    @BeforeEach
    fun setUp() {
        repository = Mockito.mock(NoteRepository::class.java)
        generator = Mockito.mock(NoteIdentifierGenerator::class.java)
        noteSaver = NoteSaver(repository, generator)
    }

    @Test
    fun `Empty title throws IllegalTitleException`() {
        assertThrows<IllegalTitleException> {
            noteSaver.save("", null)
        }
    }

    @Test
    fun `Valid title without description saves to the repository`() {
        val note = NoteMother.getValidNoteWithoutDescription()
        `Assert that the note was saved to the repository given the primitives from`(note)
    }

    @Test
    fun `Valid title with description saves to the repository`() {
        val note = NoteMother.getValidNoteWithDescription()
        `Assert that the note was saved to the repository given the primitives from`(note)
    }

    private fun `Assert that the note was saved to the repository given the primitives from`(note: Note) {
        // Setup values
        val title = NoteMother.getTitlePrimitiveFrom(note)
        val description = NoteMother.getDescriptionPrimitiveFrom(note)
        Mockito.`when`(generator.generate()).thenReturn(note.id)
        // Execute
        val result = noteSaver.save(title, description)
        // Assert
        Mockito.verify(repository, Mockito.times(1)).save(note)
        assertEquals(note, result)
    }
}
