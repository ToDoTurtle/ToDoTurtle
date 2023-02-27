package deadline.application

import deadline.domain.DeadlineRepository
import deadline.mothers.DeadlineMother
import note.mothers.NoteMother
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import kotlin.test.assertEquals

class RemoveDeadlineTest {

    private lateinit var repository: DeadlineRepository
    private lateinit var useCase: RemoveDeadline

    @BeforeEach
    fun `Set Up`() {
        repository = Mockito.mock(DeadlineRepository::class.java)
        useCase = RemoveDeadline(repository)
    }

    @Test
    fun `Note without deadline throws an exception`() {
        val noteId = NoteMother.getIdentifierFrom(NoteMother.getValidNoteWithDescription())
        assertThrows<NonExistentDeadlineException> { useCase.remove(noteId) }
    }

    @Test
    fun `Note with deadline removes the note from the deadline repository`() {
        val deadline = DeadlineMother.getValidDeadline()
        val noteId = DeadlineMother.getNoteIdentifierFromDeadline(deadline)
        Mockito.`when`(repository.search(noteId)).thenReturn(deadline)

        val removedDeadline = useCase.remove(noteId)

        assertEquals(deadline, removedDeadline)
        Mockito.verify(repository, Mockito.times(1)).delete(deadline)
    }
}
