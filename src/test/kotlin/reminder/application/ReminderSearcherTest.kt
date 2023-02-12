package reminder.application

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import reminder.domain.ReminderIdentifierGenerator
import reminder.domain.ReminderRepository
import reminder.mothers.IdentifierMother
import reminder.mothers.ReminderMother
import kotlin.test.assertEquals

class ReminderSearcherTest {
    private lateinit var generator: ReminderIdentifierGenerator
    private lateinit var reminderSearcher: ReminderSearcher
    private lateinit var repository: ReminderRepository

    @BeforeEach
    fun setUp() {
        repository = Mockito.mock(ReminderRepository::class.java)
        generator = Mockito.mock(ReminderIdentifierGenerator::class.java)
        reminderSearcher = ReminderSearcher(repository)
    }

    @Test
    fun `Nothing is returned when valid identifier doesn't exist in the repository`() {
        // Initialize
        val identifier = IdentifierMother.getValidIdentifier()
        Mockito.`when`(repository.search(identifier)).thenReturn(null)
        // Execute
        val result = reminderSearcher.search(identifier)
        // Assert
        Mockito.verify(repository, Mockito.times(1)).search(identifier)
        assertEquals(null, result)
    }

    @Test
    fun `Reminder is returned when identifier exists in the repository`() {
        // Initialize
        val reminder = ReminderMother.getValidReminderWithoutDescription()
        Mockito.`when`(repository.search(reminder.id)).thenReturn(reminder)
        // Execute
        val result = reminderSearcher.search(reminder.id)
        // Assert
        Mockito.verify(repository, Mockito.times(1)).search(reminder.id)
        assertEquals(reminder, result)
    }
}