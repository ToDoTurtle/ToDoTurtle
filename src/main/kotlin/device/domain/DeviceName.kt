package device.domain

import device.domain.exceptions.IllegalDeviceNameException

data class DeviceName(val name: String) {

    init {
        if (name.isBlank()) {
            throw IllegalDeviceNameException()
        }
    }
}
