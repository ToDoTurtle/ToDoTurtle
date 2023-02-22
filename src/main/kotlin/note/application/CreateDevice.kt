package note.application

import note.domain.Device
import note.domain.DeviceIdentifier
import note.domain.DeviceName
import note.domain.DeviceRepository
import note.domain.exceptions.AlreadyExistingDevice

class CreateDevice(private val repository: DeviceRepository) {

    /***
     * Creates a new device and saves it to the DeviceRepository
     * @throws AlreadyExistingDevice if a new device already exists
     * @return The device instance that was saved inside the repository
     */

    fun create(id: DeviceIdentifier, name: DeviceName): Device {
        val device = repository.get(id)?.let { throw AlreadyExistingDevice(id) } ?: Device(id, name)
        repository.save(device)
        return device
    }
}
