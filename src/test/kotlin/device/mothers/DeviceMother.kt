package device.mothers

import note.domain.Device

object DeviceMother {
    fun getValidDevice() = Device(
        id = DeviceIdentifierMother.getValidIdentifier(),
        name = DeviceNameMother.getValidDeviceName(),
    )

    fun getNamePrimitiveFrom(device: Device) = DeviceNameMother.getPrimitiveFrom(device.name)
}
