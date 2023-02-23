package device.domain.exceptions

import device.domain.DeviceIdentifier

class AlreadyExistingDevice(identifier: DeviceIdentifier) : Exception() {
    override val message: String = "Device with identifier $identifier already exists."
}
