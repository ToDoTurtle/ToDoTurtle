package note.domain

data class Note(val id: NoteIdentifier, val title: Title, val description: Description?)
