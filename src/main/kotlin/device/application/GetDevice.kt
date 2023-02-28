package device.application

import device.domain.Device
import device.domain.DeviceRepository
import shared.domain.Identifier

class GetDevice(
    private val repository: DeviceRepository,
) {
    /***
     * Searches a Device with the given identifier and returns it if found.
     * @see Device
     * @return null or a device instance if it exists
     */
    fun get(identifier: String) = repository.get(Identifier(identifier))
    internal fun get(identifier: Identifier) = repository.get(identifier)
}
