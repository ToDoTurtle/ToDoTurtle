package reminder.domain

interface ReminderRepository {
    fun save(reminder: Reminder)
    fun search(identifier: ReminderIdentifier): Reminder?
}