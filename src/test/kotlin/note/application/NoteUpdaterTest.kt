package note.application

import note.domain.Note
import note.domain.NoteRepository
import note.domain.exceptions.NonExistentNoteException
import note.domain.exceptions.UnchangedNoteException
import note.mothers.DescriptionMother
import note.mothers.NoteMother
import note.mothers.TitleMother
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import shared.domain.IdentifierGenerator
import shared.mothers.IdentifierMother

class NoteUpdaterTest {
    private lateinit var noteUpdater: NoteUpdater
    private lateinit var generator: IdentifierGenerator
    private lateinit var repository: NoteRepository

    private val identifier = IdentifierMother.getValidIdentifier()
    private val originalNote = NoteMother.getValidNoteWithDescription()
    private val toBeSavedNote = NoteMother.getAlternativeNoteWithDescription()

    @BeforeEach
    fun setUp() {
        repository = Mockito.mock(NoteRepository::class.java)
        generator = Mockito.mock(IdentifierGenerator::class.java)
        noteUpdater = NoteUpdater(repository, generator)
    }

    @Test
    fun `Invalid identifier throws NonExistentReminderException and doesn't call save`() {
        // Initialize
        Mockito.`when`(repository.search(identifier)).thenReturn(null)

        val title = TitleMother.getPrimitiveFrom(originalNote.title)
        val description = DescriptionMother.getPrimitiveFrom(originalNote.description!!)

        // Execute and Assert
        assertThrows<NonExistentNoteException> {
            noteUpdater.update(identifier, title, description)
        }

        // Assert number of calls
        assertRepositoryCalls(0, 0, toBeSavedNote)
    }

    @Test
    fun `Valid identifier yet unchanged note throws UnchangedNoteException and doesn't call save`() {
        // Initialize
        Mockito.`when`(repository.search(identifier)).thenReturn(toBeSavedNote)

        val sameTitle = TitleMother.getPrimitiveFrom(toBeSavedNote.title)
        val sameDescription = DescriptionMother.getPrimitiveFrom(toBeSavedNote.description!!)

        // Execute and Assert
        assertThrows<UnchangedNoteException> {
            noteUpdater.update(identifier, sameTitle, sameDescription)
        }

        // Assert number of calls
        assertRepositoryCalls(0, 0, toBeSavedNote)
    }

    @Test
    fun `Existing note with changed title and description gets deleted and saved`() {
        // Initialize
        Mockito.`when`(repository.search(identifier)).thenReturn(originalNote)
        Mockito.`when`(generator.generate()).thenReturn(identifier)

        val newTitle = TitleMother.getPrimitiveFrom(toBeSavedNote.title)
        val newDescription = DescriptionMother.getPrimitiveFrom(toBeSavedNote.description!!)

        // Execute and Assert
        noteUpdater.update(identifier, newTitle, newDescription)

        // Assert number of calls
        assertRepositoryCalls(1, 1, toBeSavedNote)
    }

    @Test
    fun `Existing note with changed title and same description gets deleted and saved`() {
        // Initialize
        Mockito.`when`(repository.search(identifier)).thenReturn(originalNote)
        Mockito.`when`(generator.generate()).thenReturn(identifier)

        val sameTitleDiferentDescriptionNote = NoteMother.getNoteWithOriginalTitleAndChangedDescription()
        val sameTitle = TitleMother.getPrimitiveFrom(sameTitleDiferentDescriptionNote.title)
        val newDescription = DescriptionMother.getPrimitiveFrom(sameTitleDiferentDescriptionNote.description!!)

        // Execute and Assert
        noteUpdater.update(identifier, sameTitle, newDescription)

        // Assert number of calls
        assertRepositoryCalls(1, 1, sameTitleDiferentDescriptionNote)
    }

    @Test
    fun `Existing note with same title and changed description gets deleted and saved`() {
        // Initialize
        Mockito.`when`(repository.search(identifier)).thenReturn(originalNote)
        Mockito.`when`(generator.generate()).thenReturn(identifier)

        val diferentTitleSameDescriptionNote = NoteMother.getNoteWithChangedTitleAndOriginalDescription()
        val sameTitle = TitleMother.getPrimitiveFrom(diferentTitleSameDescriptionNote.title)
        val newDescription = DescriptionMother.getPrimitiveFrom(diferentTitleSameDescriptionNote.description!!)

        // Execute and Assert
        noteUpdater.update(identifier, sameTitle, newDescription)

        // Assert number of calls
        assertRepositoryCalls(1, 1, diferentTitleSameDescriptionNote)
    }

    private fun assertRepositoryCalls(
        deleteCalls: Int,
        saveCalls: Int,
        toBeSavedNote: Note,
    ) {
        Mockito.verify(repository, Mockito.times(deleteCalls)).delete(toBeSavedNote.id)
        Mockito.verify(repository, Mockito.times(saveCalls))
            .save(toBeSavedNote)
    }
}
