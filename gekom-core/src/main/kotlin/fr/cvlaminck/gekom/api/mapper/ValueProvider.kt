package fr.cvlaminck.gekom.api.mapper

/**
 * Interface providing methods to extract values of properties that will be mapped to the object using a [Mapper].
 */
interface ValueProvider {

    /**
     * Returns the value and the type of the property with name [propertyName].
     *
     * @param propertyName Name of the property.
     * @return the value and the type of the property or null if the property does not exists.
     */
    fun get(propertyName: String): TypedValue<Any>?
}
