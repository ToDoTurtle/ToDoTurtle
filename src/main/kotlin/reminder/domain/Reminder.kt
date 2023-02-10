package reminder.domain

import java.util.*

data class Reminder(val id: ReminderIdentifier, val title: Title, val description: Optional<Description>)
