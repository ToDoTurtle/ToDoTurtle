package note.mothers

import note.domain.Note

object NoteMother {
    fun getValidNoteWithDescription() = Note(
        id = NoteIdentifierMother.getValidIdentifier(),
        title = TitleMother.getValidTitle(),
        description = DescriptionMother.getValidDescription(),
    )

    fun getValidNoteWithoutDescription() = Note(
        id = NoteIdentifierMother.getValidIdentifier(),
        title = TitleMother.getValidTitle(),
        description = null,
    )

    fun getAlternativeNoteWithDescription() = Note(
        id = NoteIdentifierMother.getValidIdentifier(),
        title = TitleMother.getAlternativeTitle(),
        description = DescriptionMother.getAlternativeDescription(),
    )

    fun getNoteWithOriginalTitleAndChangedDescription() = Note(
        id = NoteIdentifierMother.getValidIdentifier(),
        title = TitleMother.getValidTitle(),
        description = DescriptionMother.getAlternativeDescription(),
    )

    fun getNoteWithChangedTitleAndOriginalDescription() = Note(
        id = NoteIdentifierMother.getValidIdentifier(),
        title = TitleMother.getAlternativeTitle(),
        description = DescriptionMother.getValidDescription(),
    )

    fun getTitlePrimitiveFrom(note: Note) = TitleMother.getPrimitiveFrom(note.title)
    fun getDescriptionPrimitiveFrom(note: Note): String? =
        note.description?.let { DescriptionMother.getPrimitiveFrom(it) }
}
