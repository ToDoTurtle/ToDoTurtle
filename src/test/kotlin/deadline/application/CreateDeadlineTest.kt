package deadline.application

import deadline.domain.DeadlineRepository
import deadline.domain.exceptions.AlreadyConfiguredDeadline
import deadline.mothers.DeadlineMother
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import kotlin.test.assertEquals

class CreateDeadlineTest {

    private lateinit var repository: DeadlineRepository
    private lateinit var useCase: CreateDeadline

    @BeforeEach
    fun `Set Up`() {
        repository = Mockito.mock(DeadlineRepository::class.java)
        useCase = CreateDeadline(repository)
    }

    @Test
    fun `If a deadline is already configured, throw an exception`() {
        val currentDeadline = DeadlineMother.getValidDeadline()
        val noteIdentifier = DeadlineMother.getNoteIdentifierFromDeadline(currentDeadline)
        val identifier = noteIdentifier.toPrimitives()
        val newTime = DeadlineMother.getDifferentTimeFrom(currentDeadline).toPrimitive()

        Mockito.`when`(repository.get(noteIdentifier)).thenReturn(currentDeadline)

        assertThrows<AlreadyConfiguredDeadline> { useCase.create(identifier, newTime) }
    }

    @Test
    fun `If a deadline is not configured, save it to the repository`() {
        val deadline = DeadlineMother.getValidDeadline()
        val noteIdentifier = DeadlineMother.getNoteIdentifierFromDeadline(deadline)
        val time = DeadlineMother.getTimeFromDeadline(deadline)

        Mockito.`when`(repository.get(noteIdentifier)).thenReturn(null)
        val resultingDeadline = useCase.create(noteIdentifier, time)

        assertEquals(deadline, resultingDeadline)
        Mockito.verify(repository, Mockito.times(1)).save(deadline)
    }
}
