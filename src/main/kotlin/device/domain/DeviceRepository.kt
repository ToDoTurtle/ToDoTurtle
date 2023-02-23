package device.domain

interface DeviceRepository {
    fun save(device: Device)
    fun get(id: DeviceIdentifier): Device?
}
