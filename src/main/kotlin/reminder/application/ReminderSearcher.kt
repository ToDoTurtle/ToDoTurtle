package reminder.application

import reminder.domain.*
import java.util.*

class ReminderSearcher(
    private val repository: ReminderRepository,
) {

    /***
     * Searches a Reminder with the given identifier and returns it if found.
     * @see Reminder
     * @return An optional of a reminder instance.
     */
    fun search(identifier: ReminderIdentifier) = repository.search(identifier)

}