package reminder.mothers

import reminder.domain.Description

class DescriptionMother {

    companion object {
        private fun getPrimitiveValidDescription() = "Buy Milk"
        fun getValidDescription() = Description(getPrimitiveValidDescription())
        fun getPrimitiveFrom(description: Description): String = description.description
    }
}