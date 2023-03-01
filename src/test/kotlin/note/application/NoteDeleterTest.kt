package note.application

import note.domain.NoteRepository
import note.domain.exceptions.NonExistentNoteException
import note.mothers.NoteMother
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import shared.mothers.IdentifierMother

class NoteDeleterTest {
    private lateinit var noteDeleter: NoteDeleter
    private lateinit var repository: NoteRepository

    @BeforeEach
    fun setUp() {
        repository = Mockito.mock(NoteRepository::class.java)
        noteDeleter = NoteDeleter(repository)
    }

    @Test
    fun `Invalid identifier throws NonExistentNoteException`() {
        val id = IdentifierMother.getValidIdentifier()
        val idPrimitive = IdentifierMother.getPrimitiveFrom(id)
        Mockito.`when`(repository.search(id)).thenReturn(null)

        assertThrows<NonExistentNoteException> {
            noteDeleter.delete(idPrimitive)
        }

        Mockito.verify(repository, Mockito.times(0)).delete(id)
    }

    @Test
    fun `Existing note gets deleted from the repository`() {
        val note = NoteMother.getValidNoteWithoutDescription()
        val idPrimitive = IdentifierMother.getPrimitiveFrom(note.id)
        Mockito.`when`(repository.search(note.id)).thenReturn(note)

        assertDoesNotThrow {
            noteDeleter.delete(idPrimitive)
        }

        Mockito.verify(repository, Mockito.times(1)).delete(note.id)
    }
}
