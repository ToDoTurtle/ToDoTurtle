package reminder.mothers

import reminder.domain.Title

class TitleMother {

    companion object {
        private const val PRIMITIVE_TITLE = "Buy Milk"
        fun getValidTitle() = Title(PRIMITIVE_TITLE)
        fun getPrimitiveFrom(title: Title) = title.title
    }

}