package deadline.application

import deadline.domain.DeadlineRepository
import deadline.domain.exceptions.AlreadyConfiguredDeadline
import deadline.domain.exceptions.NonExistentDeadlineException
import deadline.mothers.DeadlineMother
import deadline.mothers.TimeMother
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import shared.domain.exceptions.InvalidUUIDException
import shared.mothers.IdentifierMother
import kotlin.test.assertEquals

class DeadlineChangerTest {

    private lateinit var repository: DeadlineRepository
    private lateinit var useCase: DeadlineChanger

    @BeforeEach
    fun `Set Up`() {
        repository = Mockito.mock(DeadlineRepository::class.java)
        useCase = DeadlineChanger(repository)
    }

    @Test
    fun `Changing a deadline that doesn't exist throws NonExistingDeadlineException`() {
        val deadline = DeadlineMother.getValidDeadline()
        Mockito.`when`(repository.search(DeadlineMother.getNoteIdentifierFromDeadline(deadline))).thenReturn(null)
        assertThrows<NonExistentDeadlineException> { useCase.change(deadline.toPrimitives()) }
    }

    @Test
    fun `Getting an identifier that isn't valid throws InvalidUUIDException`() {
        val x = DeadlinePrimitives(IdentifierMother.invalidPrimitiveIdentifier, TimeMother.getValidTime().time)
        assertThrows<InvalidUUIDException> { useCase.change(x) }
    }

    @Test
    fun `Changing a deadline that is equal as the current one throws AlreadyConfiguredDeadline`() {
        val deadline = DeadlineMother.getValidDeadline()
        Mockito.`when`(repository.search(DeadlineMother.getNoteIdentifierFromDeadline(deadline))).thenReturn(deadline)
        assertThrows<AlreadyConfiguredDeadline> { useCase.change(deadline.toPrimitives()) }
    }

    @Test
    fun `Adding a new deadline that changes the time deletes the old one and saves the new inside the repository`() {
        val oldDeadline = DeadlineMother.getValidDeadline()
        val noteIdentifier = DeadlineMother.getNoteIdentifierFromDeadline(oldDeadline)
        val newDeadline = DeadlineMother.getWithDifferentTime(oldDeadline)
        Mockito.`when`(repository.search(noteIdentifier)).thenReturn(oldDeadline)
        Mockito.`when`(repository.remove(oldDeadline)).then {
            Mockito.`when`(repository.search(noteIdentifier)).thenReturn(null)
        }

        val result = useCase.change(newDeadline.toPrimitives())

        assertEquals(newDeadline, result)
        Mockito.verify(repository, Mockito.times(1)).remove(oldDeadline)
        Mockito.verify(repository, Mockito.times(1)).create(newDeadline)
    }
}
