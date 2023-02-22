package shared.mothers

import shared.domain.Identifier

object IdentifierMother {
    private const val PRIMITIVE_REMINDER_IDENTIFIER = "414243db-8d26-4be3-bcb2-cc91c8f95957"
    fun getValidIdentifier() = Identifier(PRIMITIVE_REMINDER_IDENTIFIER)
    fun getPrimitiveFrom(identifier: Identifier) = identifier.id
}
