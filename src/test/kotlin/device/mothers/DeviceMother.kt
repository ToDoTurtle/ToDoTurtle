package device.mothers

import device.domain.Device
import shared.mothers.IdentifierMother

object DeviceMother {
    fun getValidDevice() = Device(
        id = IdentifierMother.getValidIdentifier(),
        name = DeviceNameMother.getValidDeviceName(),
    )
    fun getIdPrimitiveFrom(device: Device) = device.id.id
    fun getNamePrimitiveFrom(device: Device) = device.name.name
}
