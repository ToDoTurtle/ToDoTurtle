package device.domain

import device.domain.exceptions.InvalidUUIDException
import java.util.UUID

@Suppress("UnusedPrivateMember")
data class DeviceIdentifier(val id: String) {
    companion object {
        private const val WANTED_VERSION = 4
    }
    init {
        try {
            val uuid = UUID.fromString(id)
            if (uuid.version() != WANTED_VERSION) throw InvalidUUIDException(id)
        } catch (ex: IllegalArgumentException) {
            throw InvalidUUIDException(id)
            throw ex
        }
    }
}
