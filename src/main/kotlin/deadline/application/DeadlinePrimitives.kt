package deadline.application

/***
 * @param noteIdentifier is the identifier of the note in primitive format.
 * @param time is the time in unix time format and in Coordinated Universal Time (UTC), if you pass
 * an incorrect time format or a different timezone (UTC +/- n) the deadline will have errors.
 */
data class DeadlinePrimitives(val noteIdentifier: String, val time: ULong)
