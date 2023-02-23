package shared.mothers

import shared.domain.Identifier
import java.util.UUID

object IdentifierMother {
    private const val PRIMITIVE_REMINDER_IDENTIFIER = "414243db-8d26-4be3-bcb2-cc91c8f95957"
    fun getValidIdentifier() = Identifier(PRIMITIVE_REMINDER_IDENTIFIER)
    fun getPrimitiveFrom(identifier: Identifier) = identifier.id
    fun getDifferentValidIdentifier(id: Identifier): Identifier {
        val uuid = UUID.randomUUID().toString()
        if (uuid == id.id) return getDifferentValidIdentifier(id)
        return Identifier(uuid)
    }
}
