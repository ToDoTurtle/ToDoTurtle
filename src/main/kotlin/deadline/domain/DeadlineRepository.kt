package deadline.domain

import shared.domain.Identifier

interface DeadlineRepository {
    fun save(deadline: Deadline)
    fun get(noteIdentifier: Identifier): Deadline?
}
