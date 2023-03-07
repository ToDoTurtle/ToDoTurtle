package device.domain

data class DevicePrimitives(val id: String, val name: String) {
    fun hasSameContent(other: DevicePrimitives) = this.name == other.name
}
