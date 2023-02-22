package device.application

import device.mothers.DeviceIdentifierMother
import device.mothers.DeviceMother
import note.application.CreateDevice
import note.domain.DeviceIdentifier
import note.domain.DeviceRepository
import note.domain.exceptions.AlreadyExistingDevice
import note.domain.exceptions.InvalidUUIDException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito

class CreateDeviceTest {

    private lateinit var repository: DeviceRepository
    private lateinit var useCase: CreateDevice

    @BeforeEach
    fun setUp() {
        repository = Mockito.mock(DeviceRepository::class.java)
        useCase = CreateDevice(repository)
    }

    @Test
    fun `If trying to create a device with invalid id, InvalidUUIDException is thrown`() {
        val invalidId = DeviceIdentifierMother.getInvalidIdentifierPrimitive()

        assertThrows<InvalidUUIDException> {
            DeviceIdentifier(invalidId)
        }
    }

    @Test
    fun `If a device is already created, AlreadyExistingDeviceException is thrown`() {
        val currentDevice = DeviceMother.getValidDevice()
        Mockito.`when`(repository.get(currentDevice.id)).thenReturn(currentDevice)

        val idPrimitive = DeviceMother.getIdPrimitiveFrom(currentDevice)
        val namePrimitive = DeviceMother.getNamePrimitiveFrom(currentDevice)

        assertThrows<AlreadyExistingDevice> {
            useCase.create(idPrimitive, namePrimitive)
        }

        Mockito.verify(repository, Mockito.times(0)).save(currentDevice)
    }

    @Test
    fun `If a device is not created, save it to the repository`() {
        val currentDevice = DeviceMother.getValidDevice()
        Mockito.`when`(repository.get(currentDevice.id)).thenReturn(null)

        val idPrimitive = DeviceMother.getIdPrimitiveFrom(currentDevice)
        val namePrimitive = DeviceMother.getNamePrimitiveFrom(currentDevice)

        useCase.create(idPrimitive, namePrimitive)

        Mockito.verify(repository, Mockito.times(1)).save(currentDevice)
    }
}
