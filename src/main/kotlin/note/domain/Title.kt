package note.domain

import note.domain.exceptions.IllegalTitleException

data class Title(val title: String) {

    init {
        if (title.isBlank())
            throw IllegalTitleException()
    }

}
