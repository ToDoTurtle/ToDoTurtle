package note.domain

data class Note(val id: NoteIdentifier, val title: Title, val description: Description?) {
    fun toPrimitives() = NotePrimitives(id, title.title, description?.description)
}