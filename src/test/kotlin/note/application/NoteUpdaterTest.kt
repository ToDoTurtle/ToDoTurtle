package note.application

import note.domain.Note
import note.domain.NoteRepository
import note.domain.exceptions.NonExistentNoteException
import note.domain.exceptions.UnchangedNoteException
import note.mothers.NoteMother
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import shared.domain.Identifier
import shared.domain.exceptions.AlreadyUsedIdentifierException
import shared.mothers.IdentifierMother

class NoteUpdaterTest {
    private lateinit var noteUpdater: NoteChanger
    private lateinit var repository: NoteRepository

    private lateinit var oldNote: Note
    private lateinit var oldNoteId: Identifier
    private lateinit var oldNoteIdPrimitive: String

    @BeforeEach
    fun setUp() {
        repository = Mockito.mock(NoteRepository::class.java)
        noteUpdater = NoteChanger(repository)
        oldNote = NoteMother.getValidNoteWithDescription()
        oldNoteId = NoteMother.getIdentifierFrom(oldNote)
        oldNoteIdPrimitive = IdentifierMother.getPrimitiveFrom(oldNoteId)
    }

    @Test
    fun `Non existing note throws NonExistentNoteException and doesn't save it to the repository`() {
        val newNote = NoteMother.getNoteWithDifferentTitleFrom(oldNote)
        Mockito.`when`(repository.search(oldNoteId)).thenReturn(null)

        assertThrows<NonExistentNoteException> {
            noteUpdater.change(oldNoteIdPrimitive, newNote.toPrimitives())
        }
    }

    @Test
    fun `Unchanged note throws UnchangedNoteException and doesn't save it inside the repository`() {
        Mockito.`when`(repository.search(oldNoteId)).thenReturn(oldNote)

        assertThrows<UnchangedNoteException> {
            noteUpdater.change(oldNoteIdPrimitive, oldNote.toPrimitives())
        }
    }

    @Test
    fun `Existing note with changed title gets deleted and saved`() {
        val newNote = NoteMother.getNoteWithDifferentTitleFrom(oldNote)
        Mockito.`when`(repository.search(oldNoteId)).thenReturn(oldNote)

        noteUpdater.change(oldNoteIdPrimitive, newNote.toPrimitives())

        Mockito.verify(repository, Mockito.times(1)).remove(oldNoteId)
        Mockito.verify(repository, Mockito.times(1)).create(newNote)
    }

    @Test
    fun `Existing note with changed description gets deleted and saved`() {
        val newNote = NoteMother.getNoteWithDifferentDescriptionFrom(oldNote)
        Mockito.`when`(repository.search(oldNoteId)).thenReturn(oldNote)

        noteUpdater.change(oldNoteIdPrimitive, newNote.toPrimitives())

        Mockito.verify(repository, Mockito.times(1)).remove(oldNoteId)
        Mockito.verify(repository, Mockito.times(1)).create(newNote)
    }

    @Test
    fun `Existing note with changed title and description gets deleted and saved`() {
        val newNote = NoteMother.getNoteWithDifferentDescriptionFrom(NoteMother.getNoteWithDifferentTitleFrom(oldNote))
        Mockito.`when`(repository.search(oldNoteId)).thenReturn(oldNote)

        noteUpdater.change(oldNoteIdPrimitive, newNote.toPrimitives())

        Mockito.verify(repository, Mockito.times(1)).remove(oldNoteId)
        Mockito.verify(repository, Mockito.times(1)).create(newNote)
    }

    @Test
    fun `If the new note is different but we use the same id as the older, throw an AlreadyUsedIdentifier exception`() {
        val newNote = NoteMother.getNoteWithDifferentDescriptionFrom(NoteMother.getNoteWithDifferentTitleFrom(oldNote))
        val newNotePrimitives = newNote.toPrimitives().copy(noteId = oldNoteIdPrimitive)
        Mockito.`when`(repository.search(oldNoteId)).thenReturn(oldNote)

        assertThrows<AlreadyUsedIdentifierException> { noteUpdater.change(oldNoteIdPrimitive, newNotePrimitives) }

        Mockito.verify(repository, Mockito.times(0)).remove(oldNoteId)
        Mockito.verify(repository, Mockito.times(0)).create(newNote)
    }
}
