package note.application

import note.domain.NoteRepository
import note.domain.exceptions.NonExistentNoteException
import note.mothers.NoteMother
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito

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
        val note = NoteMother.getValidNoteWithDescription()
        val id = NoteMother.getIdentifierFrom(note)
        Mockito.`when`(repository.search(id)).thenReturn(null)

        assertThrows<NonExistentNoteException> {
            noteDeleter.delete(id)
        }

        Mockito.verify(repository, Mockito.times(0)).delete(id)
    }

    @Test
    fun `Existing note gets deleted from the repository`() {
        val note = NoteMother.getValidNoteWithoutDescription()
        Mockito.`when`(repository.search(note.id)).thenReturn(note)

        assertDoesNotThrow {
            noteDeleter.delete(note.id)
        }

        Mockito.verify(repository, Mockito.times(1)).delete(note.id)
    }
}
