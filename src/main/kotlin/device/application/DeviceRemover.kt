package device.application

import device.domain.Device
import device.domain.DeviceRepository
import device.domain.exceptions.InvalidUUIDException
import device.domain.exceptions.NonExistentDeviceException
import shared.domain.Identifier

class DeviceRemover(
    private val repository: DeviceRepository,
) {
    /**
     * Deletes a Device with the given identifier.
     * @throws InvalidUUIDException if the given identifier is not a valid UUID.
     * @throws NonExistentDeviceException if the device does not exist or is not saved on the repository.
     * @see Device
     */
    fun remove(deviceId: String) = remove(Identifier(deviceId))
    private fun remove(deviceId: Identifier) =
        DeviceSearcher(repository).search(deviceId)?.let { repository.remove(it.id) }
            ?: throw NonExistentDeviceException(deviceId)
}
