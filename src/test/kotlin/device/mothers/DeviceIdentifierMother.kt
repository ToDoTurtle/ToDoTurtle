package device.mothers

import note.domain.DeviceIdentifier

object DeviceIdentifierMother {
    private const val PRIMITIVE_DEVICE_IDENTIFIER = "414243db-8d26-4be3-bcb2-cc91c8f95957"
    fun getValidIdentifier() = DeviceIdentifier(PRIMITIVE_DEVICE_IDENTIFIER)
}
