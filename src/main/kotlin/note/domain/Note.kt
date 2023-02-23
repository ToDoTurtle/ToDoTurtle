package note.domain

import shared.domain.Identifier

data class Note(val id: Identifier, val title: Title, val description: Description?) {
    fun toPrimitives() = NotePrimitives(id.id, title.title, description?.description)
}
