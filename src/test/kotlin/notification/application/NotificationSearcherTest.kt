package notification.application

import notification.domain.Notification
import notification.domain.NotificationRepository
import notification.mothers.NotificationMother
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import shared.domain.IdentifierGenerator
import shared.mothers.IdentifierMother
import kotlin.test.assertEquals

class NotificationSearcherTest {
    private lateinit var notificationSearcher: NotificationSearcher
    private lateinit var generator: IdentifierGenerator
    private lateinit var repository: NotificationRepository

    @BeforeEach
    fun setUp() {
        repository = Mockito.mock(NotificationRepository::class.java)
        generator = Mockito.mock(IdentifierGenerator::class.java)
        notificationSearcher = NotificationSearcher(repository)
    }

    @Test
    fun `If there are no notifications for the given note identifier, return empty collection`() {
        val noteId = IdentifierMother.getValidIdentifier()
        Mockito.`when`(repository.search(noteId)).thenReturn(emptyList<Notification>())

        val result = notificationSearcher.search(IdentifierMother.getPrimitiveFrom(noteId))

        Mockito.verify(repository, Mockito.times(1)).search(noteId)
        assertEquals(emptyList<Notification>(), result)
    }

    @Test
    fun `If there is one notification for the given note identifier, return a collection containing it`() {
        val notification = NotificationMother.getValidNotification()
        val noteId = NotificationMother.getNoteIdFrom(notification)
        Mockito.`when`(repository.search(noteId)).thenReturn(listOf(notification))

        val result = notificationSearcher.search(NotificationMother.getNoteIdPrimitiveFrom(notification))

        Mockito.verify(repository, Mockito.times(1)).search(noteId)
        assertEquals(listOf(notification), result)
    }

    @Test
    fun `If there are more than one notifications for the given note identifier, return collection containing them`() {
        val notifications = NotificationMother.getValidNotifications()
        val noteId = NotificationMother.getNoteIdFrom(notifications.first())
        Mockito.`when`(repository.search(noteId)).thenReturn(notifications)

        val result = notificationSearcher.search(NotificationMother.getNoteIdPrimitiveFrom(notifications.first()))

        Mockito.verify(repository, Mockito.times(1)).search(noteId)
        assertEquals(notifications, result)
    }
}
