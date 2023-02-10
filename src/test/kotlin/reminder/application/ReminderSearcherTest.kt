package reminder.application

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import reminder.domain.Reminder
import reminder.domain.ReminderIdentifier
import reminder.domain.ReminderIdentifierGenerator
import reminder.domain.ReminderRepository
import reminder.mothers.IdentifierMother
import reminder.mothers.ReminderMother
import java.util.*
import kotlin.test.assertEquals

class ReminderSearcherTest {
    private lateinit var reminderSaver: ReminderSaver
    private lateinit var generator: ReminderIdentifierGenerator
    private lateinit var reminderSearcher: ReminderSearcher
    private lateinit var repository: ReminderRepository

    @BeforeEach
    fun setUp() {
        repository = Mockito.mock(ReminderRepository::class.java)
        generator = Mockito.mock(ReminderIdentifierGenerator::class.java)
        reminderSearcher = ReminderSearcher(repository)
        reminderSaver = ReminderSaver(repository, generator)
    }

    @Test
    fun `Nothing is returned when valid identifier doesn't exist in the repository`() {
        val identifier = IdentifierMother.getValidIdentifier()
        `Assert that the reminder was not found in the repository given the identifier`(identifier)
    }

    @Test
    fun `Reminder is returned when identifier exists in the repository`() {
        val reminder = ReminderMother.getValidReminderWithoutDescription()
        `Assert that the reminder was found in the repository given the identifier from`(reminder)
    }

    private fun `Assert that the reminder was not found in the repository given the identifier`(identifier: ReminderIdentifier) {
        Mockito.`when`(repository.search(identifier)).thenReturn(Optional.empty())
        val result = reminderSearcher.search(identifier)
        Mockito.verify(repository, Mockito.times(1)).search(identifier)
        assertEquals(Optional.empty<Reminder>(), result)
    }

    private fun `Assert that the reminder was found in the repository given the identifier from`(reminder: Reminder) {
        Mockito.`when`(repository.search(reminder.id)).thenReturn(Optional.of(reminder))
        val result = reminderSearcher.search(reminder.id)
        Mockito.verify(repository, Mockito.times(1)).search(reminder.id)
        assertEquals(Optional.of(reminder), result)
    }
}