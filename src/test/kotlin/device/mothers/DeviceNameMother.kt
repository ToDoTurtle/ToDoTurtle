package device.mothers

import device.domain.DeviceName

object DeviceNameMother {
    private const val PRIMITIVE_DEVICE_NAME = "My Device"
    private const val BLANK_PRIMITIVE_DEVICE_NAME = " "
    fun getValidDeviceName() = DeviceName(PRIMITIVE_DEVICE_NAME)
    fun getBlankDeviceNamePrimitive() = BLANK_PRIMITIVE_DEVICE_NAME
}
