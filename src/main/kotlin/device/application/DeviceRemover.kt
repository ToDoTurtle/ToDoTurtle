package device.application

import device.domain.DeviceRepository
import device.domain.exceptions.NonExistentDeviceException
import shared.domain.Identifier

class DeviceRemover(
    private val repository: DeviceRepository,
) {
    fun remove(deviceId: String) = remove(Identifier(deviceId))
    private fun remove(deviceId: Identifier) =
        DeviceSearcher(repository).search(deviceId)?.let { repository.remove(it.id) }
            ?: throw NonExistentDeviceException(deviceId)
}
