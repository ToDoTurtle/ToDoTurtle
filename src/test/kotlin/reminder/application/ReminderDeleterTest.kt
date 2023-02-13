package reminder.application

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import reminder.domain.ReminderIdentifierGenerator
import reminder.domain.ReminderRepository
import reminder.domain.exceptions.NonExistentReminderException
import reminder.mothers.IdentifierMother
import reminder.mothers.ReminderMother

class ReminderDeleterTest {
    private lateinit var reminderDeleter: ReminderDeleter
    private lateinit var generator: ReminderIdentifierGenerator
    private lateinit var repository: ReminderRepository

    @BeforeEach
    fun setUp() {
        repository = Mockito.mock(ReminderRepository::class.java)
        generator = Mockito.mock(ReminderIdentifierGenerator::class.java)
        reminderDeleter = ReminderDeleter(repository)
    }

    @Test
    fun `Invalid identifier throws NonExistentReminderException`() {
        // Initialize
        val identifier = IdentifierMother.getValidIdentifier()
        Mockito.`when`(repository.search(identifier)).thenReturn(null)
        // Execute and Assert
        assertThrows<NonExistentReminderException> {
            reminderDeleter.delete(identifier)
        }
        // Assert number of calls
        Mockito.verify(repository, Mockito.times(0)).delete(identifier)
    }

    @Test
    fun `Existing reminder gets deleted from the repository`() {
        // Initialize
        val reminder = ReminderMother.getValidReminderWithoutDescription()
        Mockito.`when`(repository.search(reminder.id)).thenReturn(reminder)
        // Execute and Assert
        assertDoesNotThrow {
            reminderDeleter.delete(reminder.id)
        }
        // Assert number of calls
        Mockito.verify(repository, Mockito.times(1)).delete(reminder.id)
    }
}