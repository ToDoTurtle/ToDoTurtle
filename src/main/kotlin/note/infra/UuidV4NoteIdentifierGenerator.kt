package note.infra

import note.domain.NoteIdentifier
import note.domain.NoteIdentifierGenerator
import java.util.*

class UuidV4NoteIdentifierGenerator : NoteIdentifierGenerator {
    override fun generate(): NoteIdentifier = NoteIdentifier(UUID.randomUUID().toString())
}
