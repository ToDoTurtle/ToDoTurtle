package reminder.application

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import reminder.domain.ReminderIdentifierGenerator
import reminder.domain.ReminderRepository
import reminder.mothers.IdentifierMother
import kotlin.test.assertEquals

class ReminderDeleterTest {
    private lateinit var generator: ReminderIdentifierGenerator
    private lateinit var reminderDeleter: ReminderDeleter
    private lateinit var repository: ReminderRepository

    @BeforeEach
    fun setUp() {
        repository = Mockito.mock(ReminderRepository::class.java)
        generator = Mockito.mock(ReminderIdentifierGenerator::class.java)
        reminderDeleter = ReminderDeleter(repository)
    }

    @Test
    fun `False is returned when called with non existent identifier`() {
        // Initialize
        val identifier = IdentifierMother.getValidIdentifier()
        Mockito.`when`(repository.delete(identifier)).thenReturn(false)
        // Execute
        val result = reminderDeleter.delete(identifier)
        // Assert
        Mockito.verify(repository, Mockito.times(1)).delete(identifier)
        assertEquals(false, result)
    }

    @Test
    fun `True is returned when called with existent identifier`() {
        // Initialize
        val identifier = IdentifierMother.getValidIdentifier()
        Mockito.`when`(repository.delete(identifier)).thenReturn(true)
        // Execute
        val result = reminderDeleter.delete(identifier)
        // Assert
        Mockito.verify(repository, Mockito.times(1)).delete(identifier)
        assertEquals(true, result)
    }
}