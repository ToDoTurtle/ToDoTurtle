package notification.application

import notification.domain.Notification
import notification.domain.NotificationRepository
import notification.mothers.NotificationMother
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import shared.domain.IdentifierGenerator
import shared.mothers.IdentifierMother
import java.util.*
import kotlin.test.assertEquals

class NotificationGetterTest {
    private lateinit var notificationGetter: NotificationGetter
    private lateinit var generator: IdentifierGenerator
    private lateinit var repository: NotificationRepository

    @BeforeEach
    fun setUp() {
        repository = Mockito.mock(NotificationRepository::class.java)
        generator = Mockito.mock(IdentifierGenerator::class.java)
        notificationGetter = NotificationGetter(repository)
    }

    @Test
    fun `If the notification doesn't exist, return null`() {
        val identifier = IdentifierMother.getValidIdentifier()
        Mockito.`when`(repository.get(identifier)).thenReturn(null)

        val result = notificationGetter.get(IdentifierMother.getPrimitiveFrom(identifier))

        Mockito.verify(repository, Mockito.times(1)).get(identifier)
        assertEquals(null, result)
    }

    @Test
    fun `If the notification exists, return it`() {
        val notification = NotificationMother.getValidNotification()
        Mockito.`when`(repository.get(notification.id)).thenReturn(notification)

        val result = notificationGetter.get(NotificationMother.getIdentifierPrimitiveFrom(notification))

        Mockito.verify(repository, Mockito.times(1)).get(notification.id)
        assertEquals(notification, result)
    }

    @Test
    fun `If there are no notifications for the given note identifier, return empty collection`() {
        val noteId = IdentifierMother.getValidIdentifier()
        Mockito.`when`(repository.getAll(noteId)).thenReturn(Collections.emptyList<Notification>())

        val result = notificationGetter.getAll(IdentifierMother.getPrimitiveFrom(noteId))

        Mockito.verify(repository, Mockito.times(1)).getAll(noteId)
        assertEquals(Collections.emptyList<Notification>(), result)
    }

    @Test
    fun `If there is one notification for the given note identifier, return a collection containing it`() {
        val noteId = IdentifierMother.getValidIdentifier()
        val notification = NotificationMother.getNotificationFromNoteId(noteId)
        Mockito.`when`(repository.getAll(noteId)).thenReturn(listOf(notification))

        val result = notificationGetter.getAll(IdentifierMother.getPrimitiveFrom(noteId))

        Mockito.verify(repository, Mockito.times(1)).getAll(noteId)
        assertEquals(listOf(notification), result)
    }

    @Test
    fun `If there are more than one notifications for the given note identifier, return collection containing them`() {
        val noteId = IdentifierMother.getValidIdentifier()
        val notifications = NotificationMother.getNotificationsFromNoteId(noteId)
        Mockito.`when`(repository.getAll(noteId)).thenReturn(notifications)

        val result = notificationGetter.getAll(IdentifierMother.getPrimitiveFrom(noteId))

        Mockito.verify(repository, Mockito.times(1)).getAll(noteId)
        assertEquals(notifications, result)
    }
}
