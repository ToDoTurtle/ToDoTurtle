package reminder.mothers

import reminder.domain.Reminder
import java.util.*

class ReminderMother {

    companion object {
        fun getValidReminderWithDescription() = Reminder(
            id = IdentifierMother.getValidIdentifier(),
            title = TitleMother.getValidTitle(),
            description = Optional.of(DescriptionMother.getValidDescription())
        )

        fun getValidReminderWithoutDescription() = Reminder(
            id = IdentifierMother.getValidIdentifier(),
            title = TitleMother.getValidTitle(),
            description = Optional.empty()
        )

        fun getTitlePrimitiveFrom(reminder: Reminder) = TitleMother.getPrimitiveFrom(reminder.title)
        fun getDescriptionPrimitiveFrom(reminder: Reminder): Optional<String> =
            reminder.description.map { DescriptionMother.getPrimitiveFrom(it) }
    }
}