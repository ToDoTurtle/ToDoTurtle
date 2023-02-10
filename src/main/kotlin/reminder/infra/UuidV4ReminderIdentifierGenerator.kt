package reminder.infra

import reminder.domain.ReminderIdentifier
import reminder.domain.ReminderIdentifierGenerator
import java.util.*

class UuidV4ReminderIdentifierGenerator : ReminderIdentifierGenerator {
    override fun generate(): ReminderIdentifier = ReminderIdentifier(UUID.randomUUID().toString())
}