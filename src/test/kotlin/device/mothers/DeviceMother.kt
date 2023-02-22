package device.mothers

import note.domain.Device

object DeviceMother {
    fun getValidDevice() = Device(
        id = DeviceIdentifierMother.getValidIdentifier(),
        name = DeviceNameMother.getValidDeviceName(),
    )
    fun getIdPrimitiveFrom(device: Device) = device.id.id
    fun getNamePrimitiveFrom(device: Device) = device.name.name
}
