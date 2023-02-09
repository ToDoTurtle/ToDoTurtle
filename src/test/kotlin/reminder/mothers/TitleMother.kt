package reminder.mothers

import reminder.domain.Title

class TitleMother {

    companion object {
        private fun getValidTitlePrimitive(): String = "I should buy some milk, because if I don't I can't do coffee"
        fun getValidTitle(): Title = Title(getValidTitlePrimitive())
        fun getPrimitiveFrom(title: Title) = title.title
    }

}