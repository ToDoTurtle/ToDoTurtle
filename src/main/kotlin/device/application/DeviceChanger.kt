package device.application

import device.domain.Device
import device.domain.DevicePrimitives
import device.domain.DeviceRepository
import device.domain.exceptions.IllegalDeviceNameException
import device.domain.exceptions.NonExistentDeviceException
import device.domain.exceptions.UnchangedDeviceException
import shared.domain.Identifier
import shared.domain.exceptions.AlreadyUsedIdentifierException
import shared.domain.exceptions.InvalidUUIDException

class DeviceChanger(
    private val repository: DeviceRepository,
) {
    /**
     * Updates a Device with the given identifier with the newDevice primitives.
     * @throws InvalidUUIDException if the new device id from the primitives or the old device identifier isn't valid
     * @throws AlreadyUsedIdentifierException if the id of the new device is already used on the repository
     * @throws NonExistentDeviceException if the old device identifier doesn't exist on the repository
     * @throws UnchangedDeviceException if the new device content is the same as the old one
     * (doesn't compare the identifier, only the content)
     * @throws IllegalDeviceNameException if the new name isn't valid
     * @return The new saved instance of Device
     */
    fun change(oldDeviceIdentifier: String, newDevice: DevicePrimitives) {
        change(Identifier(oldDeviceIdentifier), newDevice)
    }

    private fun change(oldDeviceIdentifier: Identifier, newDevice: DevicePrimitives): Device {
        assertUpdateConditions(oldDeviceIdentifier, newDevice)
        return updateDevice(oldDeviceId = oldDeviceIdentifier, newDevice = newDevice)
    }

    private fun assertUpdateConditions(oldDeviceId: Identifier, newDevice: DevicePrimitives) {
        val oldDevice = repository.search(oldDeviceId) ?: throw NonExistentDeviceException(oldDeviceId)
        if (newDevice.hasSameContent(oldDevice.toPrimitives())) throw UnchangedDeviceException(oldDeviceId)
    }

    private fun updateDevice(oldDeviceId: Identifier, newDevice: DevicePrimitives): Device {
        val result = DeviceCreator(repository).create(newDevice)
        repository.remove(oldDeviceId)
        return result
    }
}
