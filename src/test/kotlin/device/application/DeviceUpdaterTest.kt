package device.application

import device.domain.Device
import device.domain.DeviceRepository
import device.domain.exceptions.NonExistentDeviceException
import device.domain.exceptions.UnchangedDeviceException
import device.mothers.DeviceMother
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import shared.domain.Identifier
import shared.domain.exceptions.AlreadyUsedIdentifierException
import shared.mothers.IdentifierMother

class DeviceUpdaterTest {
    private lateinit var repository: DeviceRepository
    private lateinit var useCase: DeviceChanger

    private lateinit var oldDevice: Device
    private lateinit var oldDeviceId: Identifier
    private lateinit var oldDeviceIdPrimitive: String

    @BeforeEach
    fun setUp() {
        repository = Mockito.mock(DeviceRepository::class.java)
        useCase = DeviceChanger(repository)
        oldDevice = DeviceMother.getValidDevice()
        oldDeviceId = DeviceMother.getIdentifierFrom(oldDevice)
        oldDeviceIdPrimitive = IdentifierMother.getPrimitiveFrom(oldDeviceId)
    }

    @Test
    fun `Non existing device throws NonExistentDeviceException and doesn't save it to the repository`() {
        val newDevice = DeviceMother.getDeviceWithDifferentNameFrom(oldDevice)
        Mockito.`when`(repository.search(oldDeviceId)).thenReturn(null)

        assertThrows<NonExistentDeviceException> {
            useCase.change(oldDeviceIdPrimitive, newDevice.toPrimitives())
        }
    }

    @Test
    fun `Unchanged device throws UnchangedDeviceException and doesn't save it inside the repository`() {
        Mockito.`when`(repository.search(oldDeviceId)).thenReturn(oldDevice)

        assertThrows<UnchangedDeviceException> {
            useCase.change(oldDeviceIdPrimitive, oldDevice.toPrimitives())
        }
    }

    @Test
    fun `If the new device is different but we use the same id as the older, throw AlreadyUsedIdentifierException`() {
        val newDevice = DeviceMother.getDeviceWithDifferentNameFrom(oldDevice)
        val newDevicePrimitives = newDevice.toPrimitives().copy(id = oldDeviceIdPrimitive)
        Mockito.`when`(repository.search(oldDeviceId)).thenReturn(oldDevice)

        assertThrows<AlreadyUsedIdentifierException> {
            useCase.change(oldDeviceIdPrimitive, newDevicePrimitives)
        }

        Mockito.verify(repository, Mockito.times(0)).remove(oldDeviceId)
        Mockito.verify(repository, Mockito.times(0)).create(newDevice)
    }

    @Test
    fun `Existing device with changed name gets deleted and saved`() {
        val newDevice = DeviceMother.getDeviceWithDifferentNameFrom(oldDevice)
        Mockito.`when`(repository.search(oldDeviceId)).thenReturn(oldDevice)

        useCase.change(oldDeviceIdPrimitive, newDevice.toPrimitives())

        Mockito.verify(repository, Mockito.times(1)).remove(oldDeviceId)
        Mockito.verify(repository, Mockito.times(1)).create(newDevice)
    }
}
