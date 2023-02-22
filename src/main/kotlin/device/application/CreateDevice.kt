package device.application

import device.domain.Device
import device.domain.DeviceIdentifier
import device.domain.DeviceName
import device.domain.DeviceRepository
import device.domain.exceptions.AlreadyExistingDevice
import device.domain.exceptions.InvalidUUIDException

class CreateDevice(private val repository: DeviceRepository) {

    /***
     * Creates a new device from primitives and saves it to the DeviceRepository
     * @throws InvalidUUIDException if the provided id is not a valid UUIDv4 identifier
     * @throws AlreadyExistingDevice if a new device already exists
     * @return The device instance that was saved inside the repository
     */

    fun create(id: String, name: String) = create(DeviceIdentifier(id), DeviceName(name))

    /***
     * Creates a new device from domain objects and saves it to the DeviceRepository
     * @throws AlreadyExistingDevice if a new device already exists
     * @return The device instance that was saved inside the repository
     */

    fun create(id: DeviceIdentifier, name: DeviceName): Device {
        val device = repository.get(id)?.let { throw AlreadyExistingDevice(id) } ?: Device(id, name)
        repository.save(device)
        return device
    }
}
