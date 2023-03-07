package device.domain.exceptions

import shared.domain.Identifier

class UnchangedDeviceException(deviceId: Identifier) : Exception() {
    override val message: String = "Device with id: $deviceId has the same content and therefore will not be updated"
}
