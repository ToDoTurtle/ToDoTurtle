package note.domain

import note.domain.exceptions.IllegalDeviceNameException

data class DeviceName(val name: String) {

    init {
        if (name.isBlank()) {
            throw IllegalDeviceNameException()
        }
    }
}
