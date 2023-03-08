package device.mothers

import device.domain.Device
import shared.mothers.IdentifierMother

object DeviceMother {
    fun getValidDevice() = Device(
        id = IdentifierMother.getValidIdentifier(),
        name = DeviceNameMother.getValidDeviceName(),
    )

    fun getDeviceWithDifferentNameFrom(device: Device) = Device(
        id = IdentifierMother.getDifferentValidIdentifier(device.id),
        name = DeviceNameMother.getDifferentDeviceNameFrom(device.name),
    )

    fun getIdentifierFrom(device: Device) = device.id
}
