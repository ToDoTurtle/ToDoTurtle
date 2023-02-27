package deadline.application

import deadline.domain.DeadlineRepository
import deadline.domain.exceptions.AlreadyConfiguredDeadline
import deadline.mothers.DeadlineMother
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import kotlin.test.assertEquals

class DeadlineCreatorTest {

    private lateinit var repository: DeadlineRepository
    private lateinit var useCase: DeadlineCreator

    @BeforeEach
    fun `Set Up`() {
        repository = Mockito.mock(DeadlineRepository::class.java)
        useCase = DeadlineCreator(repository)
    }

    @Test
    fun `If a deadline is already configured, throw an exception`() {
        val currentDeadline = DeadlineMother.getValidDeadline()
        val noteIdentifier = DeadlineMother.getNoteIdentifierFromDeadline(currentDeadline)
        val deadlinePrimitive = DeadlineMother.getPrimitivesFrom(currentDeadline)

        Mockito.`when`(repository.get(noteIdentifier)).thenReturn(currentDeadline)

        assertThrows<AlreadyConfiguredDeadline> { useCase.create(deadlinePrimitive) }
    }

    @Test
    fun `If a deadline is not configured, save it to the repository`() {
        val deadline = DeadlineMother.getValidDeadline()
        val noteIdentifier = DeadlineMother.getNoteIdentifierFromDeadline(deadline)
        val deadlinePrimitive = DeadlineMother.getPrimitivesFrom(deadline)

        Mockito.`when`(repository.get(noteIdentifier)).thenReturn(null)
        val resultingDeadline = useCase.create(deadlinePrimitive)

        assertEquals(deadline, resultingDeadline)
        Mockito.verify(repository, Mockito.times(1)).save(deadline)
    }
}
