package reminder.mothers

import reminder.domain.Description

class DescriptionMother {

    companion object {
        private const val PRIMITIVE_DESCRIPTION = "I should buy some milk, because if I don't I can't do coffee"
        fun getValidDescription() = Description(PRIMITIVE_DESCRIPTION)
        fun getPrimitiveFrom(description: Description) = description.description
    }
}