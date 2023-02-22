package note.domain.exceptions

import note.domain.DeviceIdentifier

class AlreadyExistingDevice(identifier: DeviceIdentifier) : Exception() {
    override val message: String = "Device with identifier $identifier already exists."
}
