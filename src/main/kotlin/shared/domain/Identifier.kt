package shared.domain

import shared.domain.exceptions.InvalidUUIDException
import java.util.*

data class Identifier(val id: String) {
    companion object {
        const val WANTED_VERSION = 4
    }

    init {
        try {
            val uuid = UUID.fromString(id)
            if (uuid.version() != WANTED_VERSION) throw InvalidUUIDException(id)
        } catch (ignored: IllegalArgumentException) {
            throw InvalidUUIDException(id)
        }
    }
}
