package reminder.domain

import java.util.*

interface ReminderRepository {
    fun save(reminder: Reminder)
    fun search(identifier: ReminderIdentifier): Optional<Reminder>
}