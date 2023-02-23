package deadline.domain

/***
 * Time saved in Unix Time Format.
 * Note that, the ULong type should avoid the 2038 issue.
 */
data class Time(val time: ULong) {
    fun toPrimitive(): ULong = time
}
