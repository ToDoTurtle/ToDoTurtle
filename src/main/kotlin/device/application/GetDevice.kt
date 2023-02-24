package device.application

import device.domain.Device
import device.domain.DeviceIdentifier
import device.domain.DeviceRepository

class GetDevice(
    private val repository: DeviceRepository,
) {
    /***
     * Searches a Device with the given identifier and returns it if found.
     * @see Device
     * @return null or a device instance if it exists
     */
    fun get(identifier: DeviceIdentifier) = repository.get(identifier)
}
