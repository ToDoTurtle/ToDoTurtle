package deadline.application

import deadline.domain.Deadline
import deadline.domain.DeadlineRepository
import deadline.domain.exceptions.AlreadyConfiguredDeadline
import deadline.domain.exceptions.NonExistentDeadlineException
import shared.domain.Identifier
import shared.domain.exceptions.InvalidUUIDException

class DeadlineChanger(private val repository: DeadlineRepository) {

    /***
     * Changes the current deadline date to the new deadline values
     * @throws InvalidUUIDException if the id from the primitives isn't valid
     * @throws NonExistentDeadlineException if the deadline id from the primitives doesn't exist on the repository
     * @throws AlreadyConfiguredDeadline if the values of the current deadline are the same as the new one
     */
    fun change(newDeadline: DeadlinePrimitives): Deadline {
        val noteIdentifier = Identifier(newDeadline.noteIdentifier)
        val currentDeadline = repository.search(noteIdentifier) ?: throw NonExistentDeadlineException()
        val currentPrimitives = currentDeadline.toPrimitives()
        if (currentPrimitives == newDeadline) throw AlreadyConfiguredDeadline()
        repository.remove(currentDeadline)
        return DeadlineCreator(repository).create(newDeadline)
    }
}
