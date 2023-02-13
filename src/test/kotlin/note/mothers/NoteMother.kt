package note.mothers

import note.domain.Note

object NoteMother {
    fun getValidNoteWithDescription() = Note(
        id = IdentifierMother.getValidIdentifier(),
        title = TitleMother.getValidTitle(),
        description = DescriptionMother.getValidDescription()
    )

    fun getValidNoteWithoutDescription() = Note(
        id = IdentifierMother.getValidIdentifier(),
        title = TitleMother.getValidTitle(),
        description = null
    )

    fun getTitlePrimitiveFrom(note: Note) = TitleMother.getPrimitiveFrom(note.title)
    fun getDescriptionPrimitiveFrom(note: Note): String? =
        note.description?.let { DescriptionMother.getPrimitiveFrom(it) }
}
