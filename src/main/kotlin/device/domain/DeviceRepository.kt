package device.domain

import shared.domain.Identifier

interface DeviceRepository {
    fun create(device: Device)
    fun search(deviceId: Identifier): Device?
    fun remove(deviceId: Identifier)
}
