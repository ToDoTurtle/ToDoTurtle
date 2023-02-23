package note.domain

@Suppress("UnusedPrivateMember")
data class NoteIdentifier(private val id: String) {
    fun toPrimitives() = id
}
