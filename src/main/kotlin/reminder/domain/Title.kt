package reminder.domain

import reminder.domain.exceptions.IllegalTitleException

data class Title(val title: String) {

    init {
        if (title.isBlank())
            throw IllegalTitleException()
    }

}