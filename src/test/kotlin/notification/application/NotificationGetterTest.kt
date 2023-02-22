package notification.application

import note.mothers.IdentifierMother
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import kotlin.test.assertEquals

class NotificationGetterTest {
    private lateinit var notificationGetter: NotificationGetter
    private lateinit var generator: NotificationIdentifierGenerator
    private lateinit var repository: NotificationRepository

    @BeforeEach
    fun setUp() {
        repository = Mockito.mock(NotificationRepository::class.java)
        generator = Mockito.mock(NotificationIdentifierGenerator::class.java)
        notificationGetter = NotificationGetter(repository)
    }

    @Test
    fun `If the reminder doesnt exist, return null`() {
        val identifier = IdentifierMother.getValidIdentifier()
        Mockito.`when`(repository.get(identifier)).thenReturn(null)

        val result = notificationGetter.get(identifier)

        Mockito.verify(repository, Mockito.times(1)).get(identifier)
        assertEquals(null, result)
    }

    @Test
    fun `If the reminder exists, return it`() {
        val notification = NotificationMother.getValidNotification()
        Mockito.`when`(repository.get(notification.id)).thenReturn(notification)

        val result = notificationGetter.get(notification.id)

        Mockito.verify(repository, Mockito.times(1)).get(notification.id)
        assertEquals(notification, result)
    }
