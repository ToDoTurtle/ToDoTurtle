package deadline.domain

import note.domain.NoteIdentifier

data class Deadline(val note: NoteIdentifier, val time: Time)
