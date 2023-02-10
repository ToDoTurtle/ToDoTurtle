package reminder.domain

fun interface ReminderIdentifierGenerator {
    /***
     * Returns a ReminderIdentifier which can be used for uniquely identifying a reminder object, so, be aware in your
     * infrastructure implementation, since this method shouldn't return the same identifier to two different Reminder
     * instances.
     * @see Reminder
     * @see ReminderIdentifier
     */
    fun generate(): ReminderIdentifier
}