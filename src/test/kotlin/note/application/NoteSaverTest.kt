package note.application

import note.domain.Note
import note.domain.NoteRepository
import note.domain.exceptions.IllegalTitleException
import note.mothers.NoteMother
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import shared.domain.exceptions.AlreadyUsedIdentifierException
import shared.domain.exceptions.InvalidUUIDException
import shared.mothers.IdentifierMother
import kotlin.test.assertEquals

class NoteSaverTest {

    private lateinit var noteSaver: NoteCreator
    private lateinit var repository: NoteRepository

    @BeforeEach
    fun setUp() {
        repository = Mockito.mock(NoteRepository::class.java)
        noteSaver = NoteCreator(repository)
    }

    @Test
    fun `Empty title throws IllegalTitleException`() {
        val notePrimitives = NoteMother.getValidNoteWithDescription().toPrimitives()
        val invalidTitlePrimitives = notePrimitives.copy(title = "")
        assertThrows<IllegalTitleException> {
            noteSaver.save(invalidTitlePrimitives)
        }
    }

    @Test
    fun `Invalid identifier throws InvalidUUID error`() {
        val note = NoteMother.getValidNoteWithDescription().toPrimitives()
        val invalidIdPrimitive = note.copy(noteId = IdentifierMother.invalidPrimitiveIdentifier)
        assertThrows<InvalidUUIDException> {
            noteSaver.save(invalidIdPrimitive)
        }
    }

    @Test
    fun `If a note with the same identifier already exists, throw an AlreadyUsedIdentifierException`() {
        val note = NoteMother.getValidNoteWithDescription()
        val noteId = NoteMother.getIdentifierFrom(note)
        Mockito.`when`(repository.search(noteId)).thenReturn(note)
        assertThrows<AlreadyUsedIdentifierException> {
            noteSaver.save(note.toPrimitives())
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
        val result = noteSaver.save(note.toPrimitives())

        Mockito.verify(repository, Mockito.times(1)).create(note)
        assertEquals(note, result)
    }
}
