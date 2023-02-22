package note.mothers

import note.domain.Title

object TitleMother {
    private const val PRIMITIVE_TITLE = "Buy Milk"
    private const val ALTERNATIVE_PRIMITIVE_TITLE = "Buy pills"
    fun getValidTitle() = Title(PRIMITIVE_TITLE)
    fun getAlternativeTitle() = Title(ALTERNATIVE_PRIMITIVE_TITLE)
    fun getPrimitiveFrom(title: Title) = title.title
}
