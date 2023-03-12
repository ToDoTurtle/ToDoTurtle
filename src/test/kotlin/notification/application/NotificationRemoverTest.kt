package notification.application

import note.domain.NoteRepository
import note.domain.exceptions.NonExistentNoteException
import notification.domain.Notification
import notification.domain.NotificationRepository
import notification.mothers.NotificationMother
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito

class NotificationRemoverTest {

    private lateinit var notificationRepository: NotificationRepository
    private lateinit var noteRepository: NoteRepository
    private lateinit var notificationRemover: NotificationRemover

    @BeforeEach
    fun `Set Up`() {
        notificationRepository = Mockito.mock(NotificationRepository::class.java)
        notificationRemover = NotificationRemover(notificationRepository)
    }

    @Test
    fun `If the note identifier related to the notification doesn't exist, throw an exception`() {
        val notification = NotificationMother.getValidNotification()
        val notificationPrimitives = NotificationMother.getPrimitivesFrom(notification)

        Mockito.`when`(noteRepository.search(NotificationMother.getNoteIdFrom(notification))).thenReturn(null)

        assertThrows<NonExistentNoteException> { notificationRemover.remove(notificationPrimitives) }
    }

    @Test
    fun `If a notification with the given time doesn't exist for the given note, throw an exception`() {
        val notification = NotificationMother.getValidNotification()
        val notificationPrimitives = NotificationMother.getPrimitivesFrom(notification)
        val noteId = NotificationMother.getNoteIdFrom(notification)

        Mockito.`when`(notificationRepository.search(noteId)).thenReturn(emptyList<Notification>())

        assertThrows<NonExistentNotificationException> { notificationRemover.remove(notificationPrimitives) }
    }

    @Test
    fun `If the notification exists, remove it from the repository`() {
        val notification = NotificationMother.getValidNotification()
        val notificationPrimitives = NotificationMother.getPrimitivesFrom(notification)
        val noteId = NotificationMother.getNoteIdFrom(notification)

        Mockito.`when`(notificationRepository.search(noteId)).thenReturn(listOf(notification))
        notificationRemover.remove(notificationPrimitives)

        Mockito.verify(notificationRepository, Mockito.times(1)).remove(
            NotificationMother.getIdentifierFrom(notification),
            NotificationMother.getTimeFrom(notification)
        )
    }
}