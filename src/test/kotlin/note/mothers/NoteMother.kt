package note.mothers

import note.domain.Note

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

    fun getAlternativeNoteWithDescription() = Note(
        id = IdentifierMother.getValidIdentifier(),
        title = TitleMother.getAlternativeTitle(),
        description = DescriptionMother.getAlternativeDescription(),
    )

    fun getNoteWithOriginalTitleAndChangedDescription() = Note(
        id = IdentifierMother.getValidIdentifier(),
        title = TitleMother.getValidTitle(),
        description = DescriptionMother.getAlternativeDescription(),
    )

    fun getNoteWithChangedTitleAndOriginalDescription() = Note(
        id = IdentifierMother.getValidIdentifier(),
        title = TitleMother.getAlternativeTitle(),
        description = DescriptionMother.getValidDescription(),
    )

    fun getTitlePrimitiveFrom(note: Note) = TitleMother.getPrimitiveFrom(note.title)
    fun getDescriptionPrimitiveFrom(note: Note): String? =
        note.description?.let { DescriptionMother.getPrimitiveFrom(it) }
}
