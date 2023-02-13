package note.domain

fun interface NoteIdentifierGenerator {
    /***
     * Returns a NoteIdentifier which can be used for uniquely identifying a note object, so, be aware in your
     * infrastructure implementation, since this method shouldn't return the same identifier to two different Note
     * instances.
     * @see Note
     * @see NoteIdentifier
     */
    fun generate(): NoteIdentifier
}