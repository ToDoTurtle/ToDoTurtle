package note.application

import note.domain.NoteRepository
import note.domain.exceptions.NonExistentNoteException
import note.mothers.NoteMother
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import shared.domain.IdentifierGenerator
import shared.mothers.IdentifierMother

class NoteDeleterTest {
    private lateinit var noteDeleter: NoteDeleter
    private lateinit var generator: IdentifierGenerator
    private lateinit var repository: NoteRepository

    @BeforeEach
    fun setUp() {
        repository = Mockito.mock(NoteRepository::class.java)
        generator = Mockito.mock(IdentifierGenerator::class.java)
        noteDeleter = NoteDeleter(repository)
    }

    @Test
    fun `Invalid identifier throws NonExistentNoteException`() {
        // Initialize
        val identifier = IdentifierMother.getValidIdentifier()
        Mockito.`when`(repository.search(identifier)).thenReturn(null)
        // Execute and Assert
        assertThrows<NonExistentNoteException> {
            noteDeleter.delete(identifier)
        }
        // Assert number of calls
        Mockito.verify(repository, Mockito.times(0)).delete(identifier)
    }

    @Test
    fun `Existing note gets deleted from the repository`() {
        // Initialize
        val note = NoteMother.getValidNoteWithoutDescription()
        Mockito.`when`(repository.search(note.id)).thenReturn(note)
        // Execute and Assert
        assertDoesNotThrow {
            noteDeleter.delete(note.id)
        }
        // Assert number of calls
        Mockito.verify(repository, Mockito.times(1)).delete(note.id)
    }
}
