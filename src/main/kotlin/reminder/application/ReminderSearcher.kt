package reminder.application

import reminder.domain.Reminder
import reminder.domain.ReminderIdentifier
import reminder.domain.ReminderRepository

class ReminderSearcher(
    private val repository: ReminderRepository,
) {

    /***
     * Searches a Reminder with the given identifier and returns it if found.
     * @see Reminder
     * @return null or a reminder instance if it exists
     */
    fun search(identifier: ReminderIdentifier) = repository.search(identifier)

}