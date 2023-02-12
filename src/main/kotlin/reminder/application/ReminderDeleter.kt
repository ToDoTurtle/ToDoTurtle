package reminder.application

import reminder.domain.ReminderIdentifier
import reminder.domain.Reminder
import reminder.domain.ReminderRepository

class ReminderDeleter(
    private val repository: ReminderRepository,
) {

    /***
     * Deletes a Reminder with the given identifier and returns true if found.
     * @see Reminder
     * @return A boolean indicating if the reminder was deleted correctly.
     */
    fun delete(identifier: ReminderIdentifier) = repository.delete(identifier)
}