package note.mothers

import note.domain.NoteIdentifier

object NoteIdentifierMother {
    private const val PRIMITIVE_REMINDER_IDENTIFIER = "414243db-8d26-4be3-bcb2-cc91c8f95957"
    fun getValidIdentifier() = NoteIdentifier(PRIMITIVE_REMINDER_IDENTIFIER)
}
