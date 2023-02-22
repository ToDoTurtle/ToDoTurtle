package shared.domain


fun interface IdentifierGenerator {
    /***
     * Returns an Identifier which can be used for uniquely identifying a domain object, so, be aware
     * in you infrastructure implementation, since this method shouldn't return the same identifier to two different
     * domain instances.
     * @see Identifier
     */
    fun generate(): Identifier
}
