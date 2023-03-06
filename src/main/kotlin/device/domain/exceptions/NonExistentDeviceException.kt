package device.domain.exceptions

import shared.domain.Identifier

class NonExistentDeviceException(id: Identifier) : Exception() {
    override val message = "The device with id $id does not exist."
}
