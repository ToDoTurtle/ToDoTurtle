package device.mothers

import note.domain.DeviceName

object DeviceNameMother {
    private const val PRIMITIVE_DEVICE_NAME = "My Device"
    fun getValidDeviceName() = DeviceName(PRIMITIVE_DEVICE_NAME)
    fun getPrimitiveFrom(deviceName: DeviceName) = deviceName.name
}
