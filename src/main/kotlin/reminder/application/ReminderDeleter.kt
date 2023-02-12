package reminder.application

import reminder.domain.ReminderIdentifier
import reminder.domain.Reminder
import reminder.domain.ReminderRepository
import reminder.domain.exceptions.NonExistentReminderException

class ReminderDeleter(
    private val repository: ReminderRepository,
) {

    /***
     * Deletes a Reminder with the given identifier and returns true if found.
     * @see Reminder
     * @return A boolean indicating if the reminder was deleted correctly.
     * @throws NonExistentReminderException if the reminder does not exist or is not saved on the repository.
     */
    fun delete(identifier: ReminderIdentifier) {
        val found = ReminderSearcher(repository).search(identifier)  // TODO: Replace with elvis operator if we switch tu nullables
        if (found.isPresent) repository.delete(identifier) else throw NonExistentReminderException(identifier)
    }
}