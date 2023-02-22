package device.mothers

import device.domain.DeviceIdentifier

object DeviceIdentifierMother {
    private const val PRIMITIVE_DEVICE_IDENTIFIER = "414243db-8d26-4be3-bcb2-cc91c8f95957"
    private const val INVALID_PRIMITIVE_DEVICE_IDENTIFIER = "Hi! I\'m an invalid identifier"
    fun getValidIdentifier() = DeviceIdentifier(PRIMITIVE_DEVICE_IDENTIFIER)
    fun getInvalidIdentifierPrimitive() = INVALID_PRIMITIVE_DEVICE_IDENTIFIER
    fun getPrimitiveFrom(identifier: DeviceIdentifier) = identifier.id
}
