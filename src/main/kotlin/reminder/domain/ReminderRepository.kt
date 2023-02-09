package reminder.domain

fun interface ReminderRepository {
    fun save(reminder: Reminder)
}