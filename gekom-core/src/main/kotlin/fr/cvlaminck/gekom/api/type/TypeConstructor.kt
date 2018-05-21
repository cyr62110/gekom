package fr.cvlaminck.gekom.api.type

/**
 *
 */
interface TypeConstructor<out T: Any> {

    /**
     * Returns the ordered list of properties that must be passed to the constructor
     * to create a new object of the given type.
     */
    val injectableProperties: List<TypeProperty<Any, Any>>

    /**
     * Construct a new object of type [T] using the values passed in parameters.
     *
     * @param propertyValues Map of values to pass to the constructor for the associated [TypeProperty].
     * @return a new object of type [T].
     */
    fun create(propertyValues: Map<TypeProperty<Any, Any>, Any>): T
}
