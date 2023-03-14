package deadline.domain

import shared.domain.Identifier

interface DeadlineRepository {
    fun create(deadline: Deadline)
    fun search(noteIdentifier: Identifier): Deadline?
    fun delete(deadline: Deadline)
}
