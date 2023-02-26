package device.application

import device.domain.DeviceRepository
import device.mothers.DeviceMother
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import shared.domain.IdentifierGenerator
import shared.mothers.IdentifierMother
import kotlin.test.assertEquals

class GetDeviceTest {
    private lateinit var generator: IdentifierGenerator
    private lateinit var repository: DeviceRepository
    private lateinit var useCase: GetDevice

    @BeforeEach
    fun setUp() {
        repository = Mockito.mock(DeviceRepository::class.java)
        generator = Mockito.mock(IdentifierGenerator::class.java)
        useCase = GetDevice(repository)
    }

    @Test
    fun `Nothing is returned when valid identifier doesn't exist in the repository`() {
        // Initialize
        val identifier = IdentifierMother.getValidIdentifier()
        Mockito.`when`(repository.get(identifier)).thenReturn(null)
        // Execute
        val result = useCase.get(identifier)
        // Assert
        Mockito.verify(repository, Mockito.times(1)).get(identifier)
        assertEquals(null, result)
    }

    @Test
    fun `Device is returned when identifier exists in the repository`() {
        // Initialize
        val device = DeviceMother.getValidDevice()
        Mockito.`when`(repository.get(device.id)).thenReturn(device)
        // Execute
        val result = useCase.get(device.id)
        // Assert
        Mockito.verify(repository, Mockito.times(1)).get(device.id)
        assertEquals(device, result)
    }
}
