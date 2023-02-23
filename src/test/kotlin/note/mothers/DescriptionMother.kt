package note.mothers

import note.domain.Description

object DescriptionMother {
    private const val PRIMITIVE_DESCRIPTION = "I should buy some milk, because if I don't I can't do coffee"
    fun getValidDescription() = Description(PRIMITIVE_DESCRIPTION)
    fun getDifferentDescriptionFrom(description: Description) = Description(description.description + " junk ")
}
