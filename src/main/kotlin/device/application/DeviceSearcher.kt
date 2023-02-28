package device.application

import device.domain.Device
import device.domain.DeviceRepository
import shared.domain.Identifier

class DeviceSearcher(
    private val repository: DeviceRepository,
) {
    /***
     * Searches a Device with the given identifier and returns it if found.
     * @see Device
     * @return null or a device instance if it exists
     */
    fun get(identifier: String) = repository.search(Identifier(identifier))
    internal fun get(identifier: Identifier) = repository.search(identifier)
}
