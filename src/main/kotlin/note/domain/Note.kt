package note.domain

import shared.domain.Identifier

data class Note(val id: Identifier, val title: Title, val description: Description?) {
    fun toPrimitives() = NotePrimitives(title.title, description?.description)
}
