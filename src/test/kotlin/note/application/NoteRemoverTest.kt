package note.application

import note.domain.NoteRepository
import note.domain.exceptions.NonExistentNoteException
import note.mothers.NoteMother
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import shared.mothers.IdentifierMother

class NoteRemoverTest {
    private lateinit var noteRemover: NoteRemover
    private lateinit var repository: NoteRepository

    @BeforeEach
    fun setUp() {
        repository = Mockito.mock(NoteRepository::class.java)
        noteRemover = NoteRemover(repository)
    }

    @Test
    fun `Invalid identifier throws NonExistentNoteException`() {
        val id = IdentifierMother.getValidIdentifier()
        val idPrimitive = IdentifierMother.getPrimitiveFrom(id)
        Mockito.`when`(repository.search(id)).thenReturn(null)

        assertThrows<NonExistentNoteException> {
            noteRemover.remove(idPrimitive)
        }

        Mockito.verify(repository, Mockito.times(0)).remove(id)
    }

    @Test
    fun `Existing note gets deleted from the repository`() {
        val note = NoteMother.getValidNoteWithoutDescription()
        val idPrimitive = IdentifierMother.getPrimitiveFrom(note.id)
        Mockito.`when`(repository.search(note.id)).thenReturn(note)

        noteRemover.remove(idPrimitive)

        Mockito.verify(repository, Mockito.times(1)).remove(note.id)
    }
}
