package device.application

import device.mothers.DeviceMother
import note.application.CreateDevice
import note.domain.DeviceRepository
import note.domain.exceptions.AlreadyExistingDevice
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
    fun `If a device is already created, throw AlreadyExistingDeviceException`() {
        val currentDevice = DeviceMother.getValidDevice()
        Mockito.`when`(repository.get(currentDevice.id)).thenReturn(currentDevice)

        assertThrows<AlreadyExistingDevice> {
            useCase.create(currentDevice.id, currentDevice.name)
        }

        Mockito.verify(repository, Mockito.times(0)).save(currentDevice)
    }

    @Test
    fun `If a device is not created, save it to the repository`() {
        val currentDevice = DeviceMother.getValidDevice()
        Mockito.`when`(repository.get(currentDevice.id)).thenReturn(null)

        useCase.create(currentDevice.id, currentDevice.name)

        Mockito.verify(repository, Mockito.times(1)).save(currentDevice)
    }
}
