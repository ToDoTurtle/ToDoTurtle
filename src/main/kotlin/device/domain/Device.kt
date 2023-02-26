package device.domain

import shared.domain.Identifier

data class Device(val id: Identifier, val name: DeviceName)
