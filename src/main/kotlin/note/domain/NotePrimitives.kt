package note.domain

data class NotePrimitives(val noteId: String, val title: String, val description: String?) {
    fun hasSameContent(other: NotePrimitives) = this.title == other.title && this.description == other.description
}
