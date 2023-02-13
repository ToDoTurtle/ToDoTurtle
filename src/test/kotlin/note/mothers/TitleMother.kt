package note.mothers

import note.domain.Title

object TitleMother {
    private const val PRIMITIVE_TITLE = "Buy Milk"
    fun getValidTitle() = Title(PRIMITIVE_TITLE)
    fun getPrimitiveFrom(title: Title) = title.title
}
