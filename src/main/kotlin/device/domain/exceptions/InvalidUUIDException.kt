package device.domain.exceptions

class InvalidUUIDException(id: String) : Exception() {
    override val message = "The id $id is not a valid UUIDv4 identifier."
}
