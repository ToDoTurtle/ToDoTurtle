package note.domain

data class Note(val id: NoteIdentifier, val title: Title, val description: Description?) {
    fun toPrimitives() = NotePrimitives(title.title, description?.description)
}
