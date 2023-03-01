package device.application

import device.domain.DeviceRepository
import device.domain.exceptions.NonExistentDeviceException
import device.mothers.DeviceMother
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import shared.mothers.IdentifierMother

class DeviceRemoverTest {
    private lateinit var repository: DeviceRepository
    private lateinit var useCase: DeviceRemover

    @BeforeEach
    fun setUp() {
        repository = Mockito.mock(DeviceRepository::class.java)
        useCase = DeviceRemover(repository)
    }

    @Test
    fun `Invalid identifier throws NonExistentDeviceException`() {
        val id = IdentifierMother.getValidIdentifier()
        val idPrimitive = IdentifierMother.getPrimitiveFrom(id)
        Mockito.`when`(repository.search(id)).thenReturn(null)

        assertThrows<NonExistentDeviceException> {
            useCase.remove(idPrimitive)
        }

        Mockito.verify(repository, Mockito.times(0)).remove(id)
    }

    @Test
    fun `Existing device gets deleted from the repository`() {
        val device = DeviceMother.getValidDevice()
        val primitiveId = IdentifierMother.getPrimitiveFrom(device.id)
        Mockito.`when`(repository.search(device.id)).thenReturn(device)

        assertDoesNotThrow { useCase.remove(primitiveId) }

        Mockito.verify(repository, Mockito.times(1)).remove(device.id)
    }
}
