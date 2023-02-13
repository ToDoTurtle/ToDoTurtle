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
     * @return Unit if the reminder was deleted or throws an exception if it does not exist.
     * @throws NonExistentReminderException if the reminder does not exist or is not saved on the repository.
     */
    fun delete(identifier: ReminderIdentifier) =
        ReminderSearcher(repository).search(identifier)?.let { repository.delete(it.id) }
            ?: throw NonExistentReminderException(identifier)
}