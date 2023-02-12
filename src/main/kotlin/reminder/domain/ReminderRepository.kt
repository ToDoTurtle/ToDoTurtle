package reminder.domain

interface ReminderRepository {
    fun save(reminder: Reminder)
    fun search(identifier: ReminderIdentifier): Reminder?
    fun delete(identifier: ReminderIdentifier)
}