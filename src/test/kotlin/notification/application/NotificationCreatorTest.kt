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
import kotlin.test.assertEquals

class NotificationCreatorTest {

    private lateinit var notificationRepository: NotificationRepository
    private lateinit var noteRepository: NoteRepository
    private lateinit var notificationCreator: NotificationCreator

    @BeforeEach
    fun `Set Up`() {
        notificationRepository = Mockito.mock(NotificationRepository::class.java)
        noteRepository = Mockito.mock(NoteRepository::class.java)
        notificationCreator = NotificationCreator(notificationRepository, noteRepository)
    }

    @Test
    fun `If the note identifier related to the notification doesn't exist, throw an exception`() {
        val notification = NotificationMother.getValidNotification()
        val noteId = NotificationMother.getNoteIdentifierFrom(notification)
        val notificationPrimitives = NotificationMother.getPrimitivesFrom(notification)

        Mockito.`when`(noteRepository.search(noteId)).thenReturn(null)

        assertThrows<NonExistentNoteException> { notificationCreator.create(notificationPrimitives) }
    }

    @Test
    fun `If a notification is already configured for the given time, throw an exception`() {
        val notification = NotificationMother.getValidNotification()
        val noteId = NotificationMother.getNoteIdentifierFrom(notification)
        val notificationPrimitives = NotificationMother.getPrimitivesFrom(notification)

        Mockito.`when`(noteRepository.search(noteId)).thenReturn(NotificationMother.getNoteFrom(notification))
        Mockito.`when`(notificationRepository.search(noteId)).thenReturn(listOf(notification))

        assertThrows<AlreadyConfiguredNotification> { notificationCreator.create(notificationPrimitives) }
    }

    @Test
    fun `If a notification is not configured for the given time, save it to the repository`() {
        val notification = NotificationMother.getValidNotification()
        val noteId = NotificationMother.getNoteIdentifierFrom(notification)
        val notificationPrimitives = NotificationMother.getPrimitivesFrom(notification)

        Mockito.`when`(noteRepository.search(noteId)).thenReturn(NotificationMother.getNoteFrom(notification))
        Mockito.`when`(notificationRepository.search(noteId)).thenReturn(emptyList<Notification>())
        val result = notificationCreator.create(notificationPrimitives)

        assertEquals(notification, result)
        Mockito.verify(notificationRepository, Mockito.times(1)).create(notification)
    }
}
