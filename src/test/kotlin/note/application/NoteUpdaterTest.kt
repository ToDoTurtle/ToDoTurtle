package note.application

import note.domain.NoteRepository
import note.domain.exceptions.NonExistentNoteException
import note.domain.exceptions.UnchangedNoteException
import note.mothers.NoteMother
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import shared.domain.exceptions.AlreadyUsedIdentifierException
import shared.mothers.IdentifierMother

class NoteUpdaterTest {
    private lateinit var noteUpdater: NoteUpdater
    private lateinit var repository: NoteRepository

    @BeforeEach
    fun setUp() {
        repository = Mockito.mock(NoteRepository::class.java)
        noteUpdater = NoteUpdater(repository)
    }

    @Test
    fun `Non existing note throws NonExistentNoteException and doesn't save it to the repository`() {
        val nonExistingNote = NoteMother.getValidNoteWithDescription()
        val nonExistingNoteId = NoteMother.getIdentifierFrom(nonExistingNote)
        val nonExistingNoteIdPrimitive = IdentifierMother.getPrimitiveFrom(nonExistingNoteId)
        val newNote = NoteMother.getNoteWithDifferentTitleFrom(nonExistingNote)
        Mockito.`when`(repository.search(nonExistingNoteId)).thenReturn(null)

        assertThrows<NonExistentNoteException> {
            noteUpdater.update(nonExistingNoteIdPrimitive, newNote.toPrimitives())
        }
    }

    @Test
    fun `Unchanged note throws UnchangedNoteException and doesn't save it inside the repository`() {
        val note = NoteMother.getValidNoteWithoutDescription()
        val id = IdentifierMother.getPrimitiveFrom(NoteMother.getIdentifierFrom(note))
        val identifier = NoteMother.getIdentifierFrom(note)
        Mockito.`when`(repository.search(identifier)).thenReturn(note)

        assertThrows<UnchangedNoteException> {
            noteUpdater.update(id, note.toPrimitives())
        }
    }

    @Test
    fun `Existing note with changed title gets deleted and saved`() {
        val oldNote = NoteMother.getValidNoteWithDescription()
        val oldNoteId = NoteMother.getIdentifierFrom(oldNote)
        val oldNoteIdPrimitive = IdentifierMother.getPrimitiveFrom(oldNoteId)

        val newNote = NoteMother.getNoteWithDifferentTitleFrom(oldNote)
        Mockito.`when`(repository.search(oldNoteId)).thenReturn(oldNote)

        noteUpdater.update(oldNoteIdPrimitive, newNote.toPrimitives())

        Mockito.verify(repository, Mockito.times(1)).delete(oldNoteId)
        Mockito.verify(repository, Mockito.times(1)).save(newNote)
    }

    @Test
    fun `Existing note with changed description gets deleted and saved`() {
        val oldNote = NoteMother.getValidNoteWithDescription()
        val oldNoteId = NoteMother.getIdentifierFrom(oldNote)
        val oldIdPrimitive = IdentifierMother.getPrimitiveFrom(oldNoteId)
        val newNote = NoteMother.getNoteWithDifferentDescriptionFrom(oldNote)
        Mockito.`when`(repository.search(oldNoteId)).thenReturn(oldNote)

        noteUpdater.update(oldIdPrimitive, newNote.toPrimitives())

        Mockito.verify(repository, Mockito.times(1)).delete(oldNoteId)
        Mockito.verify(repository, Mockito.times(1)).save(newNote)
    }

    @Test
    fun `Existing note with changed title and description gets deleted and saved`() {
        val oldNote = NoteMother.getValidNoteWithDescription()
        val oldNoteId = NoteMother.getIdentifierFrom(oldNote)
        val oldNoteIdPrimitive = IdentifierMother.getPrimitiveFrom(oldNoteId)
        val newNote = NoteMother.getNoteWithDifferentDescriptionFrom(NoteMother.getNoteWithDifferentTitleFrom(oldNote))
        Mockito.`when`(repository.search(oldNoteId)).thenReturn(oldNote)

        noteUpdater.update(oldNoteIdPrimitive, newNote.toPrimitives())

        Mockito.verify(repository, Mockito.times(1)).delete(oldNoteId)
        Mockito.verify(repository, Mockito.times(1)).save(newNote)
    }

    @Test
    fun `If the new note is different but we use the same id as the older, throw an AlreadyUsedIdentifier exception`() {
        val oldNote = NoteMother.getValidNoteWithDescription()
        val oldNoteId = NoteMother.getIdentifierFrom(oldNote)
        val oldNoteIdPrimitive = IdentifierMother.getPrimitiveFrom(oldNoteId)
        val newNote = NoteMother.getNoteWithDifferentDescriptionFrom(NoteMother.getNoteWithDifferentTitleFrom(oldNote))
        val newNotePrimitives = newNote.toPrimitives().copy(noteId = oldNoteIdPrimitive)
        Mockito.`when`(repository.search(oldNoteId)).thenReturn(oldNote)

        assertThrows<AlreadyUsedIdentifierException> { noteUpdater.update(oldNoteIdPrimitive, newNotePrimitives) }

        Mockito.verify(repository, Mockito.times(0)).delete(oldNoteId)
        Mockito.verify(repository, Mockito.times(0)).save(newNote)
    }
}
