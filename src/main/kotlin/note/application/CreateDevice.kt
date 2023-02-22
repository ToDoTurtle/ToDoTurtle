package note.application

import note.domain.Device
import note.domain.DeviceIdentifier
import note.domain.DeviceName
import note.domain.DeviceRepository
import note.domain.exceptions.AlreadyExistingDevice
import note.domain.exceptions.InvalidUUIDException

class CreateDevice(private val repository: DeviceRepository) {

    /***
     * Creates a new device and saves it to the DeviceRepository
     * @throws InvalidUUIDException if the provided id is not a valid UUIDv4 identifier
     * @throws AlreadyExistingDevice if a new device already exists
     * @return The device instance that was saved inside the repository
     */

    fun create(id: String, name: String): Device {
        val identifier = DeviceIdentifier(id)
        val device = repository.get(identifier)?.let { throw AlreadyExistingDevice(identifier) } ?: Device(
            id = identifier,
            name = DeviceName(name),
        )
        repository.save(device)
        return device
    }
}
