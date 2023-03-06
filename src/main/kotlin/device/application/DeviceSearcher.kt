package device.application

import device.domain.Device
import device.domain.DeviceRepository
import shared.domain.Identifier
import shared.domain.exceptions.InvalidUUIDException

class DeviceSearcher(
    private val repository: DeviceRepository,
) {
    /***
     * Searches a Device with the given identifier and returns it if found.
     * @throws InvalidUUIDException if the identifier isn't valid.
     * @see Device
     * @return null or a device instance if it exists
     */
    fun search(identifier: String) = search(Identifier(identifier))
    internal fun search(identifier: Identifier) = repository.search(identifier)
}
