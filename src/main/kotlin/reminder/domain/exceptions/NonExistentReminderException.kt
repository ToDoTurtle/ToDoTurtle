package reminder.domain.exceptions

import reminder.domain.ReminderIdentifier

class NonExistentReminderException(identifier: ReminderIdentifier) : Exception() {
    override val message: String = "Reminder with identifier $identifier does not exist or is not saved on the repository."
}