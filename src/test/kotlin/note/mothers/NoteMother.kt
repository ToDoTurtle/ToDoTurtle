package note.mothers

import note.domain.Note
import shared.mothers.IdentifierMother

object NoteMother {
    fun getValidNoteWithDescription() = Note(
        id = IdentifierMother.getValidIdentifier(),
        title = TitleMother.getValidTitle(),
        description = DescriptionMother.getValidDescription(),
    )

    fun getValidNoteWithoutDescription() = Note(
        id = IdentifierMother.getValidIdentifier(),
        title = TitleMother.getValidTitle(),
        description = null,
    )

    fun getNoteWithDifferentTitleFrom(note: Note) = note.copy(
        id = IdentifierMother.getDifferentValidIdentifier(note.id),
        title = TitleMother.getDifferentTitleFrom(note.title),
    )

    fun getNoteWithDifferentDescriptionFrom(note: Note): Note {
        val description = note.description?.let { DescriptionMother.getDifferentDescriptionFrom(it) }
            ?: DescriptionMother.getValidDescription()
        return note.copy(id = IdentifierMother.getDifferentValidIdentifier(note.id), description = description)
    }

    fun getIdentifierFrom(note: Note) = note.id
}
