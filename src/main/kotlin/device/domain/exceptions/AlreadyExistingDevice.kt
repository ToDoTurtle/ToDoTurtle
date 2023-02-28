package device.domain.exceptions

import shared.domain.Identifier

class AlreadyExistingDevice(identifier: Identifier) : Exception() {
    override val message: String = "Device with identifier $identifier already exists."
}
