package shared.domain.exceptions

class InvalidUUIDException(id: String) : Exception() {
    override val message = "Invalid UUIDv4 identifier: $id"
}
