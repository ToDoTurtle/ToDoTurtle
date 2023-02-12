package reminder.application

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import reminder.domain.Reminder
import reminder.domain.ReminderIdentifierGenerator
import reminder.domain.ReminderRepository
import reminder.domain.exceptions.IllegalTitleException
import reminder.mothers.ReminderMother
import kotlin.test.assertEquals

class ReminderSaverTest {

    private lateinit var reminderSaver: ReminderSaver
    private lateinit var generator: ReminderIdentifierGenerator
    private lateinit var repository: ReminderRepository

    @BeforeEach
    fun setUp() {
        repository = Mockito.mock(ReminderRepository::class.java)
        generator = Mockito.mock(ReminderIdentifierGenerator::class.java)
        reminderSaver = ReminderSaver(repository, generator)
    }

    @Test
    fun `Empty title throws IllegalTitleException`() {
        assertThrows<IllegalTitleException> {
            reminderSaver.save("", null)
        }
    }

    @Test
    fun `Valid title without description saves to the repository`() {
        val reminder = ReminderMother.getValidReminderWithoutDescription()
        `Assert that the reminder was saved to the repository given the primitives from`(reminder)
    }

    @Test
    fun `Valid title with description saves to the repository`() {
        val reminder = ReminderMother.getValidReminderWithDescription()
        `Assert that the reminder was saved to the repository given the primitives from`(reminder)
    }

    private fun `Assert that the reminder was saved to the repository given the primitives from`(reminder: Reminder) {
        // Setup values
        val title = ReminderMother.getTitlePrimitiveFrom(reminder)
        val description = ReminderMother.getDescriptionPrimitiveFrom(reminder)
        Mockito.`when`(generator.generate()).thenReturn(reminder.id)
        // Execute
        val result = reminderSaver.save(title, description)
        // Assert
        Mockito.verify(repository, Mockito.times(1)).save(reminder)
        assertEquals(reminder, result)
    }


}