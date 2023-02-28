package device.application

import device.domain.Device
import device.domain.DeviceName
import device.domain.DevicePrimitives
import device.domain.DeviceRepository
import device.domain.exceptions.AlreadyExistingDevice
import device.domain.exceptions.IllegalDeviceNameException
import device.domain.exceptions.InvalidUUIDException
import shared.domain.Identifier

class DeviceCreator(private val repository: DeviceRepository) {

    /***
     * Creates a new device from primitives and saves it to the DeviceRepository
     * @throws InvalidUUIDException if the provided id is not a valid UUIDv4 identifier
     * @throws AlreadyExistingDevice if a new device already exists
     * @throws IllegalDeviceNameException if the provided name is blank
     * @return The device instance that was saved inside the repository
     */

    fun create(primitives: DevicePrimitives) = create(Identifier(primitives.id), DeviceName(primitives.name))

    /***
     * Creates a new device from domain objects and saves it to the DeviceRepository
     * @throws AlreadyExistingDevice if a new device already exists
     * @return The device instance that was saved inside the repository
     */

    internal fun create(id: Identifier, name: DeviceName): Device {
        val device = repository.search(id)?.let { throw AlreadyExistingDevice(id) } ?: Device(id, name)
        repository.create(device)
        return device
    }
}
