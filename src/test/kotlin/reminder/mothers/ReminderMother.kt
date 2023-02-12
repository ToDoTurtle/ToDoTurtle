package reminder.mothers

import reminder.domain.Reminder

class ReminderMother {

    companion object {
        fun getValidReminderWithDescription() = Reminder(
            id = IdentifierMother.getValidIdentifier(),
            title = TitleMother.getValidTitle(),
            description = DescriptionMother.getValidDescription()
        )

        fun getValidReminderWithoutDescription() = Reminder(
            id = IdentifierMother.getValidIdentifier(),
            title = TitleMother.getValidTitle(),
            description = null
        )

        fun getTitlePrimitiveFrom(reminder: Reminder) = TitleMother.getPrimitiveFrom(reminder.title)
        fun getDescriptionPrimitiveFrom(reminder: Reminder): String? =
            reminder.description?.let { DescriptionMother.getPrimitiveFrom(it) }
    }
}