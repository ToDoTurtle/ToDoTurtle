package note.application

import note.domain.*
import note.domain.exceptions.NonExistentNoteException
import note.mothers.IdentifierMother
import note.mothers.NoteMother
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito

class NoteUpdaterTest {
    private lateinit var noteUpdater: NoteUpdater
    private lateinit var generator: NoteIdentifierGenerator
    private lateinit var repository: NoteRepository

    private val newTitle = "title"
    private val newDescription = "description"
    private val identifier = IdentifierMother.getValidIdentifier()
    private val note = NoteMother.getValidNoteWithDescription()

    @BeforeEach
    fun setUp() {
        repository = Mockito.mock(NoteRepository::class.java)
        generator = Mockito.mock(NoteIdentifierGenerator::class.java)
        noteUpdater = NoteUpdater(repository, generator)
    }

    @Test
    fun `Invalid identifier throws NonExistentReminderException and doesn't call save`() {
        // Initialize
        val identifier = IdentifierMother.getValidIdentifier()
        Mockito.`when`(repository.search(identifier)).thenReturn(null)

        // Execute and Assert
        assertThrows<NonExistentNoteException> {
            noteUpdater.update(identifier, newTitle, newDescription)
        }

        // Assert number of calls
        assertNumberOfCalls(0, 0)
    }

    @Test
    fun `Existing note gets deleted and saved`() {
        // Initialize
        Mockito.`when`(repository.search(identifier)).thenReturn(note)
        Mockito.`when`(generator.generate()).thenReturn(identifier)

        // Execute and Assert
        noteUpdater.update(identifier, newTitle, newDescription)

        // Assert number of calls
        assertNumberOfCalls( 1, 1)
    }

    private fun assertNumberOfCalls(
        deleteCalls: Int,
        saveCalls: Int,
    ) {
        Mockito.verify(repository, Mockito.times(deleteCalls)).delete(identifier)
        Mockito.verify(repository, Mockito.times(saveCalls))
            .save(NoteMother.getValidNoteFrom(newTitle, newDescription))
    }
}