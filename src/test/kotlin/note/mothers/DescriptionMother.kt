package note.mothers

import note.domain.Description

object DescriptionMother {
    private const val PRIMITIVE_DESCRIPTION = "I should buy some milk, because if I don't I can't do coffee"
    private const val ALTERNATIVE_PRIMITIVE_DESCRIPTION = "I should remember buying the pills for my mother"
    fun getValidDescription() = Description(PRIMITIVE_DESCRIPTION)
    fun getAlternativeDescription() = Description(ALTERNATIVE_PRIMITIVE_DESCRIPTION)
    fun getPrimitiveFrom(description: Description) = description.description
}
