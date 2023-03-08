package device.application

import device.domain.DeviceName
import device.domain.DeviceRepository
import device.domain.exceptions.IllegalDeviceNameException
import device.mothers.DeviceMother
import device.mothers.DeviceNameMother
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import shared.domain.exceptions.AlreadyUsedIdentifierException

class DeviceCreatorTest {

    private lateinit var repository: DeviceRepository
    private lateinit var useCase: DeviceCreator

    @BeforeEach
    fun setUp() {
        repository = Mockito.mock(DeviceRepository::class.java)
        useCase = DeviceCreator(repository)
    }

    @Test
    fun `If trying to create a device with blank name, IllegalDeviceNameException is thrown`() {
        val blankDeviceName = DeviceNameMother.getBlankDeviceNamePrimitive()

        assertThrows<IllegalDeviceNameException> {
            DeviceName(blankDeviceName)
        }
    }

    @Test
    fun `If a device is already created, AlreadyExistingDeviceException is thrown`() {
        val currentDevice = DeviceMother.getValidDevice()
        Mockito.`when`(repository.search(currentDevice.id)).thenReturn(currentDevice)

        assertThrows<AlreadyUsedIdentifierException> {
            useCase.create(currentDevice.toPrimitives())
        }

        Mockito.verify(repository, Mockito.times(0)).create(currentDevice)
    }

    @Test
    fun `If a device is not created, save it to the repository`() {
        val currentDevice = DeviceMother.getValidDevice()
        Mockito.`when`(repository.search(currentDevice.id)).thenReturn(null)

        useCase.create(currentDevice.toPrimitives())

        Mockito.verify(repository, Mockito.times(1)).create(currentDevice)
    }
}
