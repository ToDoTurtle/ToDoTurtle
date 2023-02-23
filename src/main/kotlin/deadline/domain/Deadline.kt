package deadline.domain

import shared.domain.Identifier

data class Deadline(val noteId: Identifier, val time: Time)
