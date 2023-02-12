package reminder.mothers

import reminder.domain.ReminderIdentifier

class IdentifierMother {

    companion object {
        private const val PRIMITIVE_REMINDER_IDENTIFIER = "414243db-8d26-4be3-bcb2-cc91c8f95957"
        fun getValidIdentifier() = ReminderIdentifier(PRIMITIVE_REMINDER_IDENTIFIER)
    }
}