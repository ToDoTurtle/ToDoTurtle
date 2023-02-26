package device.domain

import shared.domain.Identifier

interface DeviceRepository {
    fun save(device: Device)
    fun get(deviceId: Identifier): Device?
}
