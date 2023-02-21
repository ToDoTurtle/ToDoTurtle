package deadline.mothers

import deadline.domain.Time

object TimeMother {
    fun getValidTime() = Time(200UL)
    fun getDifferentTime(time: Time) = Time(time.time + 2UL)
}
